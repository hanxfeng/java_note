- # 数据类型

| 数据类型     | 描述                                                                  |
| -------- | ------------------------------------------------------------------- |
| nil      | 只有值nil属于该类，表示一个无效值，在条件表达式中代表 false                                  |
| boolean  | 包含两个值，false和true                                                    |
| number   | 表示双精度类型的实浮点数                                                        |
| string   | 字符串                                                                 |
| function | 由 C 或 LUA 编写的函数                                                     |
| table    | 关联数组，索引可以是数字，字符串或表类型，在Lua里table创建<br>是通过构造表达式完成，最简单的构造表达式是{}，用于创建空表 |

- # 变量
  
	- lua 声明变量是时不需要指定数据类型，只需要指定变量名和值即可，举例：
	  
		- `local str = "hello`
		
		-
		  
		- 
	
	- ## table
	
		- table 变量比较特殊，既是数组又是字典，取决于定义形式。
		  
		- 当以  `local map = {name = "jack", age = 21}` 形式定义时，可以使用 `map.name` 获取到值。
		
		- 当以 `local arr = {"java", "python", "C"}` 形式定义时，可以使用 `arr[1]` 来获取到值，索引从 1 开始
	
	- ## 循环
	  
		```Lua
		local arr = {"Python", "Java", "C"}
		local map = {name = "jack", age = 21}
		
		for index, value in ipairs(arr) do
			print(index, value)
		end
		
		for key, value in pairs(map) do
			print(ket, value)
		end
		```