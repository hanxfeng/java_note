package org.example.javastudy.算法.双指针;
/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */

/*
首先，要明确位置 i 的雨水量该如何计算，它的水量为左右两边高度较低的减去位置 i 的高度
这里的左右两边高度指的是位置 i 左边最高的高度和右边最高的高度
因此可以使用双指针来解答此题
创建两个指针 left 和 right,left的初始值为0,right 的初始值为索引最大值
创建两个变量，leftMax 和 righ他Max 来保存左边最高的高度和右边最高的高度
当左指针高度低于右指针时，更新左最大高度（先更新后更新无影响），计算出此处接水量，然后左指针向右移动一格
当右指针高度低于左指针水，更新右最大高度，计算出此处接水量，然后右指针向左移动一格，直到左右指针重合
如果 leftMax = 3,height[left]=1,rightMax=height[right]=2,时，似乎出现错误
但是如果有 当 height[left]=3 时，因为右指针高度始终低于左指针高度，因此左指针不会移动到
height[left]=1 的位置
 */
class Solution7 {
    public int trap(int[] height) {
        int ans = 0;
        int left = 0,right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left += 1;
            }
            else {
                ans += rightMax - height[right];
                right -= 1;
            }
        }
        return ans;
    }
}