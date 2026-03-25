package org.example.javastudy.算法.回溯;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        int n = nums.length;
        for (int num:nums) {
            output.add(num);
        }

        backtrack(n,output,res,0);
        return res;
    }
    public void backtrack(int n, List<Integer> output, List<List<Integer>> res,int first) {
        if(first == n) {
            res.add(new ArrayList(output));
        }

        for (int i = first; i < n; i++) {
            // Collections.swap 用于交换数组中索引为 first 和 i 的值
            Collections.swap(output,first,i);
            backtrack(n,output,res,first+1);
            Collections.swap(output,first,i);
        }
    }
}