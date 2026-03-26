package org.example.javastudy.算法.回溯;

import java.util.ArrayList;
import java.util.List;
/*
1<<n 等价于 2^n ，但相比于幂运算，左移运算速度更快，效率更高
(mask & (1<<i) 是按位与运算， 1<<i 会生成一个第i位是1其他位是0的整数，用于判断mask的第i位是否是0
如果mask的第i位是0,那么该运算结果为0,否则为非0
因为 t 是 ArrayList 是一种可变集合对象，所以如果将 ArrayList 直接加入数组中，那么数组中的
ArrayList 会随着t的改变而改变，因此需要new一个新的ArrayList加入res
 */
class Solution1  {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> t = new ArrayList<Integer>();
        int n = nums.length;
        for (int mask = 0; mask< (1<<n);mask++) {
            t.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1<<i))!=0) {
                    t.add(nums[i]);
                }
            }
            res.add(new ArrayList<Integer>(t));
        }
        return res;
    }
}