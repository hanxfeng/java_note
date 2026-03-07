package org.example.javastudy.算法.双指针;
/*
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。
 */

/*
定义两个指针，左指针初始值为0，右指针初始值为给定数组的最大索引。定义最大容纳量，初始为0
计算两个指针所在位置构成容器的容纳量，与最大容纳量对比，大于最大容纳量则将最大容纳量更新为当前容纳量
将比较左右指针对应的数的大小，移动数较小的指针，左指针右移，右指针左移。
遍历结束后就能得到最大容纳量
 */
class Solution5 {
    public int maxArea(int[] height) {
        int length = height.length-1;
        int left = 0;
        int right = length;
        int max_ans = 0;
        while (left < right) {
            int ans = Math.min(height[left],height[right]) * (right - left);
            max_ans = Math.max(ans,max_ans);
            if (height[left] < height[right]) {
                left += 1;
            }
            else {
                right -= 1;
            }
        }
        return max_ans;
    }
}
