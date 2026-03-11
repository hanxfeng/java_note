package org.example.javastudy.算法.普通数组;
/*
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。



示例 1:

输入: nums = [1,2,3,4]
输出: [24,12,8,6]
示例 2:

输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
 */

/*
对于每个位置 i，最终结果等于它左边所有元素的乘积乘以它右边所有元素的乘积。
所以，可以定义一个数组 ans，先保存左边的乘积，再计算右边的乘积
 */
class Solution16 {
    public int[] productExceptSelf(int[] nums) {
        int R = 1;
        int n = nums.length;
        int[] ans = new int[n];

        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }
        return ans;
    }
}
