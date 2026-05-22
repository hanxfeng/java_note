- 概念：
	- 使用java操作关系型数据库的一套API
- DriverManager（驱动管理类）：
	- 作用：
		1. 注册驱动
			- 虽然使用 `Class.forName("com.mysql.jdbc.Driver")` 实现，但底层还是使用的DriverManager
		2. 获取数据库连接
	- 常用方法：
		- 语法：`static Connection | getConnection(String url, String user, String password)`
			- 作用：用于建立与数据库的连接对象
			- url：
				- 语法：`jdbc:mysql://ip地址:端口号/数据库名称?参数键值对1&参数键值对2`
				- 示例：`jdbc:mysql://127.0.0.1:3306/study?useSSL=false`
				- 如果是使用本地的数据库可以省略 ip 和端口号
			- user：登录数据库的用户名
			- password：登录数据库的密码
		- 示例：`Connection conn = getConnection(url, user, password);`
- Connection（数据库连接对象）：
	- 作用：
		1. 获取执行 SQL 的对象
		2. 管理事务
	- 获取执行SQL 的对象：
		- 普通执行 SQL 对象：`Statement createStatement()`
		- 预编译 SQL 的 SQL 执行对象，防止 SQL 注入
			- `PreparedStatement prepareStatement(sql)` 
		- 执行储存过程的对象（不常用）：`CallableStatement prepareCall(sql)`
		- 使用前需要先创建 Connection 对象，例：
			- `Statement sttm = conn.createStatement();`
	- 管理事务：
		- 开启事务：`conn.setAutoCommit(boolean autoCommit)`
			- false 为手动提交事务，true 为自动提交事务
		- 提交事务：`conn.commit()`
		- 回滚事务：`conn.rollback()`
		- 注意：
			- 一般是在 try - catch 中执行，在 try 中开启事务，出现错误后回滚，正常执行则提交
- Statement：
	- 作用：执行 SQL 语句
	- 语法：
		- `int executeUpdate(sql)`
			- 执行 DML，DDL 语句，返回值代表 DML语句影响的行数，DDL执行成功返回0
		- `ResultSet executeQuery(sql)`
			- 执行 DQL 语句，返回值为 ResultSet 结果集对象
	- 使用前要先获取执行 SQL 的对象 Statement
		- `Statement sttm = conn.createStatement();`
	- 然后通过 sttm 调用：`sttm.executeUpdate(sql);`
- ResultSet（结果集对象）：
	- 作用：封装了 DQL 语句的查询结果，可以从中获取查询到的结果
	- 常用方法：
		- `boolean next()`：
			- 返回值：
				- true：当前行有数据，是有效行
				- false：当前行无数据，是无效行
			- 作用：将光标向前移动一行，并判断当前行是否是有效行
		- `E getE(参数);`
			- 返回值：获取到的数据
			- 参数：
				- 可以传入 int 或 String，int 是列编号（索引），从 1 开始，String 是列名称
			- 作用：获取数据，E 指任意类型数据
- PreparedStatement（）：
	- 作用：预编译 SQL 对象并执行 SQL 语句，用于防止 SQL 注入
	- 使用：
		1. 设置 sql ：`select * from study where username = ? and password = ?`
			- 与 Statement 不同，查询条件不再使用字符串拼接而是使用 ? 作为占位符
		2. 获取 PreparedStatement 对象：`PreparedStatement pstmt  = conn.preparedStatement(sql);
		3. 设置 ? 的值：`pstmt.setE(int index, E value);`
			- index：要替换的 ? 占位符的序号，第一个就是 1，第二个是 2
			- value：要替换的 ? 占位符的内容，比如第一个 ? 替换为 张三
		4. 执行 sql：`ResultSet rs = pstmt.executeQuery();`