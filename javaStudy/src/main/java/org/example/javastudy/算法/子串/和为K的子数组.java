package org.example.javastudy.算法.子串;
/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。

示例 1：

输入：nums = [1,1,1], k = 2
输出：2

示例 2：

输入：nums = [1,2,3], k = 3
输出：2

 */
import java.util.HashMap;
/*
核心看不懂，硬背吧
 */

class Solution10 {
    public int subarraySum(int[] nums, int k) {
        int pre = 0, count = 0;
        HashMap<Integer,Integer> mp = new HashMap<Integer, Integer>();
        mp.put(0,1);
        for (int num : nums) {
            pre += num;
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
