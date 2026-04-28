- # 对于类似秒杀业务的优化

	- 假设有一个优惠券抢购业务，其后端实现流程如下：查询优惠券是否存在，是否可以进行购买 -> 判断优惠券库存是否充足 -> 查询下单用户是否下过订单，保证每人只能买一个 -> 如果用户之前没有购买过，则修改数据库中的数据，扣减库存 -> 创建订单数据
	
	- 这一流程串行执行，最终耗时为所有操作耗时之和，查询优惠券，查询订单，修改库存数据都会对数据库进行操作，因为数据库本身并发较差，所以在高并发状态下整体性能会比较差
	
	- ## 优化
	
		- 可以将部分业务转移到 Redis 中，提高并发能力。
		
		- 可以将查询库存和查询下单数据记录到 Redis 中，然后异步读取 Redis 中的数据进行订单创建，减少响应时间。
		
		- 库存可以 String 进行储存，用户可以用 Set 类型的数据储存，保证不重复。
		  
		- 先将库存数量储存到 Redis 中，如果有用户进行下单，则先判断是否存在于 Set 数据中，如果不存在则库存数量 - 1,向 Set 数据中添加用户 id。然后就可以异步的获取Set 数据中的用户id,来创建订单数据
		
		- 因为要保证原子性，所以上述操作需要在 Lua 脚本中进行
	
	- ## 代码示例：
	```java
	// 假设库存已保存至 Redis 中
	public class 
		// 省略对 Redis 框架和 Mapper 的注入代码
		private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
		static {
		UNLOCK_SCRIPT = new DefaultRedisScript<>();
		// ClassPathResource 可以直接获取 Resource 目录下的文件
		UNLOCK_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
		UNLOCK_SCRIPT.setResultType(Long.class);
		}
		
		// BlockingQueue 是阻塞队列当队列为空时，获取元素的线程会等待直到队列非空；
		// 当队列已满时，添加元素的线程会等待直到队列有空闲位置
		private BlockingQueue<voucherOrder> orderTasks = new ArrayBlockingQueue
			<>(1024*1024);
		
		// 创建一个线程池
		private static final ExecutorService SECKILL_ORDER_EXECUTOR = 
			Executor.newSingleThreadExecutor();
		
		@PostConstruct // 该注解表示在类初始化时执行下方的方法
		private void init() {
			SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler);
		}
		
		private class VoucherOrderHandler implements Runnable {
			@Override
			public void run {
				while (true) {
					try{
						// 1. 获取队列中的订单信息
						VoucherOrder voucherOrder = orderTasks.take();
						// 2. 创建订单
						// handlerVoucherOrder 中实现了获取用户 -> 获取锁 -> 
						// 将数据写入数据库 -> 释放锁
						handlerVoucherOrder(voucherOrder);
						}
					catch (Exception e) {
						log.error("出现错误");
						}
				}
			 }
		}
		
		@Override
		public R seckillVoucher (Long voucherId) {
			// 获取用户 id
			Long userID = user.getId();
			// 1.执行 lua 脚本
			Long result = stringRedisTemplate.execute{
				SECKILL_SCRIPT,
				Collection.emptyList(), // lua 脚本中没有 key 变量，因此传入空集合
				voucherId.toString(), userId
			}
			
			// 2. 判断结果，不为 0 则返回错误信息
			int r = result.intValue();
			if (result != 0) {
				return R.error(r == 1 ? "库存不足" : "不能重复下单");
			}
			
			// 3.结果为 0，有购买资格，将下单信息保存至阻塞队列
			Long orderId = redisIdWorker.nextId("order");
			// 3.1 获取订单 id
			voucherOrder.setId(rederId);
			// 3.2 获取用户 id
			voucherOrder.serUserId(userId);
			// 3.3 获取优惠券 id
			voucherOrder.setVoucherId(voucherId);
			// 3.4 将数据放入阻塞队列
			orderTasks.add(voucherOrder);
			
			return R.success(orderId)
		}
	```
	- ## Lua 脚本 seckill.lua
	```Lua
	-- 1.参数列表
	-- 1.1 优惠券 id
	local voucherId = ARGV[1]
	
	-- 1.2 用户 id
	local userId = ARGV[2]
	
	-- 2.数据 key
	-- 2.1 库存 key
	local stockKey = 'seckill:stock'..voucherId -- Lua 中..表示 +
	
	-- 2.2 订单 key
	local orderKey = 'seckill:order'..voucherId
	
	-- 3.脚本业务
	-- 3.1 判断库存是否充足
	-- tonumber 表示将数据类型转为数字
	if (tonumber(redis.call('get', 'stockKey')) <= 0) then
		-- 库存不足，返回 1
		return 1
	end
	
	-- 3.2 判断用户是否下过单
	-- sismember 用于查询 Redis 中是否已经存在输入的 key-value 数据
	if (redis.call('sismember', orderKey, userId) == 1) then
		-- 返回 1 代表存在数据，重复下单，返回 2
		return 2
	end
	
	-- 3.3 扣库存
	-- incrby 用于将 key 的值 + 1，这里加 -1 是 - 1 的意思
	redis.call('incrby' stockKey, -1)
	
	-- 3.4 下单（保存用户）
	-- sadd 是操作集合数据的命令，作用是向集合中添加数据，可以一次添加多个
	redis.call('sadd', orderKey, userId)
	
	return 0
	```