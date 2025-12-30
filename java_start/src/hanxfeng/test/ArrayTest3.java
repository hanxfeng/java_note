package hanxfeng.test;

import java.util.Arrays;

public class ArrayTest3 {
    public static void main(String[] args) {
        //定义数据
        int []  arr = new int [] {33, 5, 22, 44, 55,33};
        int []  arr2 = new int [arr.length];

        //进行数据打乱+
        for (int i = arr.length -1;i >= 0; i--) {
            arr2[arr.length - i - 1] = arr[i];
        }

        //输出数组arr2
        System.out.println(Arrays.toString(arr2));
    }
}
