package org.example.javastudy.算法.哈希表;
/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。



示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
import java.util.HashSet;
import java.util.Set;

/*
使用一个 hashSet 集合存储数据，避免了数据重复的问题，将数据都放入这个 hashSet 中
遍历数据找到连续序列的开头，即 hashSet 中没有比这个数小1的数
然后统计比这个数大1的数的数量，最终结果就是连续序列的长度
因为是在for循环中进行，最后会比较最长序列长度和当前序列长度，因此即使有多个连续序列，最终也之后输出最长的连续序列的长度

 */
class Solution3 {
    public int longestConsecutive(int[] nums) {
        // 创建一个 hashSet
        Set<Integer> num_set = new HashSet<Integer>();
        // 将数组中的数据添加到 hashSet 中
        for (int num : nums) {
            num_set.add(num);
        }

        // 保存最长序列的长度
        int longestStreak = 0;

        for (int num : num_set) {
            // 只有序列开头才会向下进行
            if (!num_set.contains(num-1)){
                // 保存当前数字和当前序列长度
                int currentNum = num;
                int currentStreak = 1;
                // 如果存在比序列开头正好大1个的数字，那么使当前数字和当前长度+1
                while(num_set.contains(currentNum +1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                // 对比当前长度和最长长度，将大的那个保存为最长长度
                longestStreak = Math.max(longestStreak,currentStreak);
            }

        }
        return longestStreak;
    }
}
