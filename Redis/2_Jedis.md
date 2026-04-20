- # Jedis

	- ## 概述：
		- Jedis 是 java 中用来操作 Redis 数据库的包
	
	- ## 快速入门：
		1.  引入依赖
			
			```xml
			<dependency>
				<groupId>redis.clients</groupId>
				<artifaactId>jedis</artifaactId>
				<version>3.7.0</version>
			</dependency>
			```
		
		2. 建立连接 
		
			```java
			private Jedis jedis;
			
			@BeforeEach
			public void setUp() {
				// 建立连接
				jedis = new Jedis("127.0.0.0",6379);
				// 设置密码
				jedis.auth("123456");
				// 选择库
				jedis.select(0);
			}
			```
	
		3. 测试 redis
	
		```java
		@Test
		public void test() {
			// 插入数据，方法名就是 redis 命令名称
			String result = jedis.set("name", "xiaoming");
			// 获取数据
			String name = jedis.get("name");
		}
		```
	
		4. 释放资源
	
	```java
		@AfterEach
		public void tearDown() {
			if (jedis != null) {
				jedis.close;
			}
		}
	```



- # Jedis 连接池

	- ## 概述：
	
		- Jedsi 本身线程不安全，而且频繁的创建和销毁连接会有性能损耗，因此推荐使用 Jedis 连接池代替 Jedis 直连方式
	
	- ## 创建连接池
	
	- ```java
	  public class JedisConnectionFactory{
		  private static final JedisPool jedisPool;
		  
		  static {
		  // 创建连接池设置
		  JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		  
		  // 设置最大连接数为8
		  jedisPoolConfig.setMaxTotal(8);
		  
		  // 设置最小连接数为0
		  jedisPoolConfig.setIdle(0);
		  
		  // 设置最长等待时间，单位是 ms
		  jedisPoolConfig.setMaxWaitMillis(200);
		  
		  // 创建 Jedis 连接池
		  String ip = "127.0.0.1";
		  String port = "6379";
		  String password = "123456";
		  int timeout = 1000; // 超时时间
		  jedisPoll = new JedisPoll(jedisPoolConfig, ip, port, timeout, password);
		  }
		  
		  // 提供获取 Jedis 对象的方法
		  public static Jedis getJedis() {
			  return jedisPoll.getResource();
		  }
	}
	
	// 修改建立连接的代码
	private Jedis jedis;
	
	@BeforeEach
	public void setUp() {
		// 建立连接
		// jedis = new Jedis("127.0.0.0",6379);
		jedis = JedisConnectionFactory.getJedis();
		// 设置密码
		jedis.auth("123456");
		// 选择库
		jedis.select(0);
	}
	  ```
	  