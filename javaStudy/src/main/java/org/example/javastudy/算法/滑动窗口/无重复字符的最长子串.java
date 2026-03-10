package org.example.javastudy.算法.滑动窗口;
/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串的长度。

示例 1:

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。

示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

 */
import java.util.HashSet;
import java.util.Set;
/*
定义两个指针 left 和 right ，外层循环控制 left ，内层循环控制 right，变量 ans 储存最长子串长度
要找到不重复的子串，可以使用 Set 集合，来判断子串长度
将右指针指向的字母添加到 Set 集合中，然后右指针向前一格，判断向前一格后指向的字符是否存在于 Set 集合中
如果存在则内循环结束，right - left + 1 就是当前最长不重复子串的长度，与最长子串长度比较取最大
左指针向前一格，然后在 Set 集合中删除之前指向的字符，重新开始内循环
外循环结束后输出最长子串长度即可
 */
class Solution8 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<Character>();
        int right = -1;
        int n = s.length();
        int ans = 0;

        for (int left = 0; left < n; left ++) {
            if (left != 0) {
                occ.remove(s.charAt(left-1));
            }
            while(right + 1 < n && !occ.contains(s.charAt(right + 1))) {
                occ.add(s.charAt(right + 1));
                right += 1;
            }
            ans = Math.max(ans,right - left + 1);
        }
        return ans;
    }
}
