package org.example;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int lengthOfLIS(int[] nums) {
        int[] d = new int[nums.length+1];
        d[0] = 1;
        // maxNum 是当前最大递增的数的下标
        int maxNum = 0;
        for (int i = 1; i < nums.length; i++) {
            d[i] = 1;
            for (int j = 0; j<i;j++) {
                if (nums[i] > nums[j]) {
                    d[i] = Math.max(d[i], d[j] + 1);
                }
            }
            maxNum = Math.max(maxNum,d[i]);
        }
        System.out.println(Arrays.toString(d));
        System.out.println(maxNum);
        return  d[maxNum];
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {0,1,0,3,2,3}));
    }
}

