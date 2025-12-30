package hanxfeng.test;

import java.util.Scanner;

public class ArrayTest1 {
    public static void main(String[] args) {
        //定义数据
        int []  arr = new int [] {33, 5, 22, 44, 55,33};
        boolean result = false;

        //获取键盘输入的数字
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        int n = sc.nextInt();

        //进行查询
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == n) {
                result = true;
                System.out.println(j);  //如果存在输出索引
                break;
            }
        }

        //如果不存在则输出“数据不存在”
        if (!result) {
            System.out.println("该数据不存在");
        }
    }
}

