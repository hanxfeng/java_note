package org.example.javastudy.算法.普通数组;
/*
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。



示例 1：

输入：nums = [1,2,0]
输出：3
解释：范围 [1,2] 中的数字都在数组中。

示例 2：

输入：nums = [3,4,-1,1]
输出：2
解释：1 在数组中，但 2 没有。

示例 3：

输入：nums = [7,8,9,11,12]
输出：1
解释：最小的正数 1 没有出现。
 */
/*
方法非正式名称：原地哈希：对数组进行整理，使数字 i 位于下标 i - 1 处，然后再遍历数组
第一个 i + 1 ！= nums[i] 的，就是缺失的正整数 i + 1
代码思路：先遍历一边数组，将大于0的，小于数组最大下标的，并且 nums[i] != i + 1 的
进行调换，将nums[i] 调换到 i 处。遍历完毕后进行第二次遍历，判断nums[i] 是否等于 i + 1
如果存在，返回第一个 i+1,不存在返回 n+1
使用nums[nums[i]-1]!=nums[i]，防止出现死循环，例如[3,1,3] 第一次交换后为[1,3,3]
第二轮循环不进行操作，第三轮循环就会进入死循环
为什么要加nums[i]<n，防止出现数组越界
 */
class Solution17 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i]>0 && nums[i]<n && nums[nums[i]-1]!=nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
    public void swap (int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}