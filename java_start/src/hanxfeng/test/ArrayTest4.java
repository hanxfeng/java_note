package hanxfeng.test;

import java.util.Arrays;
import java.util.Random;

public class ArrayTest4 {
    public static void main(String[] args) {
        //创建数据
        int []  arr = new int [10];
        int n = 0;

        //导入random
        Random random = new Random();

        //向数组中添加随机数据
        for (int i = 0; i < arr.length; i++) {
            n =  random.nextInt(10);
            boolean flag = false;
            for (int j : arr) {
                if (n==j){
                    flag = true;
                }
            }
            if (!flag) {
                arr[i] = n;
            }
        }

        //输出数组
        System.out.println(Arrays.toString(arr));
    }
}
