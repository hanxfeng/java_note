- 不可变集合：
	- 概述：不可被修改的集合
	- 格式：
		`List<E> 集合名 = List.of(集合内容);`
		`Set<E> 集合名 = Set.of(集合内容);`
			创建不可变 Set 集合时，需要保证传入集合的内容具有唯一性
		`Map<K,V> 集合名 = Map.of(集合内容);`
			创建不可变 Map 集合时，需要保证传入的键值对的键是不能重复的
			创建不可变 Map 集合时，最多传入 20 个元素，即十个键值对
			如果要传入的键值对对象超过十个，需要使用 ofEntries 方法
			`Map 集合名 = Map.ofEntries(键值对数组);`
				 `Map<Object,Object> map = Map.ofEntries(可变Map集合名.entrySet().toArray(new Map.Entry[0]));`
				jdk10 后将上述代码简化为：`Map<Object,Object> map = Map.copyof(可变Map集合);`
- Stream 流
	- 概述：
		- Stream 是 Java 8 引入的全新 API，提供了一种声明式、函数式的数据处理方式，可以将多个操作连接起来形成数据处理流水线。Stream 流中的 API 可以分为两种，中间方法和最终方法，中间方法调用后还可以调用其他方法，最终方法调用后结束 Stream 流，不能再调用其他方法
	- 基本使用方法：
		- `list.stream().操作方法(s -> 语句);`
			- `list`：表示可以创建 Stream 流的集合
			- `.stream()`：创建一条 Stream 流
			- `.操作方法`：Stream 流中的 API
			- `s` ：依次表示流水线上的每一个数据
			- 语句：具体对 s 进行各种操作的代码，比如`System.out.println(s)`输出
	- 不同数据创建 Stream 流：
		- 单列集合：`Stream<String> steam1 = 单列集合名.stream();`
		- 双列集合：不能直接使用 Stream 流，需要先转为单列集合再创建。
			- 使用 keyset/valueSet 将键或值转为单列集合再调用 Stream
			- 使用 entrySet 将键值对转为单列集合后再调用 Stream
		- 数组：`Arrays.stream(数组);`
		- 零散数据（需要同种数据类型）：`Stream.of(零散数据);`
		- 注意：
			- `Stream.of` 方法的形参是可变参数，因此可以传递零散数据，也可以传递数组，但是只能传递引用数据类型的数组，如果传入基本数据类型的数组，会将数组当成一个元素传入 Stream 流，即传入数组的地址值
	- Stream 流的中间方法
		- 使用：在 Stream 流后跟`.方法()`
		- 注意：
			- 中间方法会返回一个新的 Stream 流，原来的 Stream 流只使用一次，使用后即关闭，使用链式编程简化编程
			- 修改 Stream 流中的数据不会影响原来集合或数组的数据
		- filter 方法：
			- 用于进行过滤，可以传入一个匿名内部类（重写test方法）/Lambda 表达式，在匿名内部类/Lambda 表达式中书写过滤语句，返回 true 表示流下数据，false 表示舍弃数据
		- limit 方法：
			- 用于获取前 n 个元素，输入参数是整数，代表获取元素的数量
		- skip 方法：
			- 用于跳过前 n 个元素，输入参数是整数，代表跳过元素的数量
		- distinct 方法：
			- 用于进行去重，不输入参数，直接调用即可，依赖 hashCode 和 equals 方法去重，需要自定义规则可以重写这两个方法
		- concat 方法：
			- 用于合并两个流，如果两个流类型不一致，会将两个流的数据类型转为它们共同的父类
			- 用法：`Stream.concat(流1,流2);` 
		- map 方法：
			- 用于转换流中的数据类型，接收匿名内部类（重写apply方法）/Lambda 表达式，需要定义两个类型1，一个是传入数据类型，一个是转换后的，输出是转换完成后的数据。转换不止类型转换，也可以是类似 `"张三-15"` 转为 15
			- 示例：`list.Stream().map(s -> Integer.parseInt(s.split("-")[1]))`
				- 代码使用 Lambda 表达式将`"张三-15"` 转为 15
	- Stream 流的最终方法：
		- forEach 方法：
			- 用于遍历流中的数据，传入匿名内部类（重写accept方法）/Lambda 表达式，方法体中书写对每个变量的处理方式例如打印每一个变量
			- 示例：`list.Stream().forEach(s -> System.out.peintln(s));`
				- 打印流中的每一个元素
		- count 方法：
			- 用于进行统计，无输入，输出为 Long 类型整数，统计流中元素的个数
		- toArray 方法：
			- 用于将流中的数据返回至一个数组中
			- 无输入参数：
				- 用于将流中的数据返回至一个 Object 类型的数组中
			- 有输入参数：
				- 输入参数为匿名内部类（重写apply方法）/Lambda 表达式，只需要将方法中默认的 Object 类型改为自己需要的类型即可，示例：
					- `String[] arr = list.Stream().toArray(new IntFunction<String[]> {`
					- `@Override`
					- `public String[] apply(int value) {`
						- `return new String[value];`
						`}});`
					- 上述代码使用匿名内部类重写了 apply 方法，将流中的数据返回到一个 String 类型的数组中
					- Lambda 表达式写法：`list.Stream().toArray(value -> new String[value]);`
		- collect 方法：
			- 用于收集流中的数据，然后返回至集合中
			- 收集到 List 集合：
				- `list.Stream().collect(Collectors.toList());`
			- 收集到 Set 集合
				- `list.Stream().collect(Collectors.toSet());`
			- 收集到 Map 集合
				- 收集到 Map 集合中时需要先规定好键是什么，值是什么
				- 使用：`.collect(Collectors.toMap());`
				- toMap：
					- 参数一表示键的生成规则
						- Function 泛型一：表示流中每一个数据的类型
						-                泛型二：表示 Map 集合中键的数据类型
						- 方法 apply 形参：依次表示流中的每一个数据
						-               方法体：生成键的代码
						-               返回值：已经生成的键
					- 参数二表示值的生成规则
						- Function 泛型一：表示流中每一个数据的类型
						-                泛型二：表示 Map 集合中值的数据类型
						- 方法 apply 形参：依次表示流中的每一个数据
						-               方法体：生成值的代码
						-               返回值：已经生成的值
				- 注意：键不能出现重复
				- 代码示例：
					- ![[toMap代码示例.png]]
