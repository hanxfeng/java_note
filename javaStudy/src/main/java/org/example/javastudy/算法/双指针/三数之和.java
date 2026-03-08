package org.example.javastudy.算法.双指针;
/*
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
 */

/*
思路还是嵌套遍历，首先对数组进行从小到大的排序，有 first， second， third 三个指针
先从 first 进行遍历，对于每个 first，使初始 second 为 first + 1，初始 third 为最大索引
同时定义一个中间值 target ，它的值为 -nums[first] ，因为数组为从小到大排列，因此 nums[first] 必然最小
这样当 nums[second] + nums[third] == target 时，则有 nums[first] + nums[second] + nums[third] = 0
在循环进行时，先移动 third 指针，当 third 指针于 second 指针重合时，最内层循环结束，second 指针向右移动一格
当 second 指针移动至数组末尾时，中间层循环结束，first 指针向右移动一格。这样就可以遍历到每一个数

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution6 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // 用于对数据进行升序排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}


