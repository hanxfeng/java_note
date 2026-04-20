- # 概述：
	
	- SpringData 是 Spring 中数据操作的模块，包含对各种数据库的集成，对 Redis 集成的模块就叫做 SpringDataRedis 。


- # 常用命令

| 方法                          | 返回值类型           | 说明                  |
| --------------------------- | --------------- | ------------------- |
| redisTemplate.opsForValue() | ValueOperations | 操作==String==类型数据    |
| redisTemplate.opsForHash()  | HashOperations  | 操作==Hash==类型数据      |
| redisTemplate.opsForList()  | ListOperations  | 操作==List==类型数据      |
| redisTemplate.opsForSet()   | SetOperations   | 操作==Set==类型数据       |
| redisTemplate.opsForZSet    | ZSetOperations  | 操作==SortedSet==类型数据 |
| redisTemplate               |                 | 通用命令                |


- # 快速入门

	- ## 1.引入依赖
	- ```xml
	  <!--Redis依赖-->
	  <dependency>  
	    <groupId>org.springframework.boot</groupId>  
	    <artifactId>spring-boot-starter-data-redis</artifactId>  
	</dependency>
	<!-- 连接池依赖-->
	<dependency>  
	    <groupId>org.apache.commons</groupId>  
	    <artifactId>commons-poll2</artifactId>    
	</dependency>
	  ```
	
	- ## 2.配置文件
	```yaml
	spring:
		redis:
			host: 127.0.0.1
			port: 6379
			password: 123456
			lettuce:
				pool:
					max-active: 8 # 最大连接数
					max-idle: 8 # 最大空闲连接
					min-idle: 0 # 最小空闲连接
					max-wait: 100 # 连接等待时间
	```
	
	- ##
	- ## 3.注入
	```java
	@Autowired
	private RedisTemplate redisTemplate;
	```
	
	- ## 4.测试
	```java
	@Test
	public void test() {
		// 插入一条 String 数据
		redisTemplate.opsForValue().set("name", "xiaoming");
		
		// 读取一条 String 数据
		redisTemplate.opsForValue().get("name");
	}
	```


- # SpringDataRedis 自定义序列化

	```java
	@Configuration  
	public class RedisConfig extends CachingConfigurerSupport {  
	    @Bean  
	    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory) {  
	        // 创建 RedisTemplate 对象  
	        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();  
  
	        // 设置连接工厂  
	        redisTemplate.setConnectionFactory(connectionFactory);  
  
	        // 创建 json 序列号工具  
	        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();  
  
	        // 设置 key 序列化  
	        redisTemplate.setKeySerializer(RedisSerializer.string());  
	        redisTemplate.setHashKeySerializer(RedisSerializer.string());  
  
	        // 设置 value 序列化  
	        redisTemplate.setValueSerializer(jsonRedisSerializer);  
	        redisTemplate.setHashValueSerializer(jsonRedisSerializer);  
  
	        // 返回  
	        return redisTemplate;  
	    }  
	}
	```


- # Spring 默认提供的序列化器

	- ## 概述：
		- 之前手动设置的序列化器存在一定问题，为了在反序列化时知道对象的1类型，会在储存数据时会额外储存一个 class 类型的数据，带来额外开销。储存的数据示例如下：
			```json
			{
				"@class": "com.hxf.redis.Entity.User",
				"name": "xiaoming",
				"age": 16
			}
			```
		
		- 为了节省内存空间，一般不会使用 json 序列化器来处理 value 而是统一使用 String 序列化器，要求只能储存 String 类型的 key 和 value，当需要储存 java 对象时，手动进行序列化和反序列化。Spring 提供了一个叫做 StringRedisTemplate 的类，它的 key 和 value 默认使用 String 序列化器，省去了自定义 RedisTemplate 的过程，在注入时注入 StringRedisTemplate 而不是 RedisTemplate 即可，以下是一个示例：
		```java
		@Autowired
		private StringRedisTemplate stringRedisTemplate;
		
		private static final ObjectMapper mapper = new ObjectMapper();
		
		@Test
		public void test() throws JsonProcessingException {
			// 准备对象
			User user = new User("xiaoming", 16);
			// 手动进行序列化
			String json = mapper.writeValueAsString(user);
			// 写入数据到Reis
			stringRedisTemplate.opsValue().set("user:200", json);
			
			// 读取数据
			String val = stringRedisTemplate.opsValue().get("user:200");
			// 反序列化
			User user1 = mapper.readValue(val,User.class);
		}
		```