package org.example.javastudy.算法.双指针;
/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。



示例 1:

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:

输入: nums = [0]
输出: [0]
 */

/*
定义左右两个指针
如果右指针指向的数不为0
交换左右指针上的数
左指针向右移动
无论右指针指向的数是否是0，右指针都向右移动
 */
class Solution4 {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = 0;

        while(right < length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left += 1;
            }
            right += 1;
        }
    }
    public void swap (int[] nums, int left, int right) {
        int temp = nums[right];
        nums[right] = nums[left];
        nums[left] = temp;
    }
}
