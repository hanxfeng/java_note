package org.example.javastudy.算法.哈希表;/*
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。



示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */


import java.util.HashMap;
import java.util.Map;

/*
整体思路是在数组中进行循环，在循环时，先计算出与当前数对应的另一个数 comp，两数相加是目标数target
然后检查哈希表中有没有这个数 comp，如果有，返回这两个数在数组中的索引，没有则进行下一步
将当前数作为键，当前数的索引作为值，保存到哈希表中
循环结束后没有找到则返回空数组
 */
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        // 新建一个 hashMap 储存数据
        Map<Integer,Integer> hashTable = new HashMap<Integer,Integer>();
        // 进行循环
        for (int i = 0; i <= nums.length;i++) {
            // 计算出要找到的数 comp
            Integer comp = target - nums[i];
            // containsKey 方法用于判断是否存在与传入数据相同的键
            if (hashTable.containsKey(comp)) {
                // 如果存在则使用 get 方法得到这个数的索引，和当前索引组成数组后返回
                return new int[] {hashTable.get(comp),i};
            }
            // 如果不存在则将当前数的作为键，数在数组中的索引作为值添加到哈希表中
            hashTable.put(nums[i],i);
        }
        return new int[0];}
}

