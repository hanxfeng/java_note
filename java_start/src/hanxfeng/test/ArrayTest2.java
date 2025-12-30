package hanxfeng.test;

public class ArrayTest2 {
    public static void main(String[] args) {
        //定义数据
        int []  arr = new int [] {33, 5, 22, 44, 55,33};
        int max = 0;

        //通过循环找到最大值
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }

        //输出最大值
        System.out.println("最大值为：");
        System.out.println(max);
    }
}
