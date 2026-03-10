package org.example.javastudy.算法.子串;
/*
给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口子串

，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。

测试用例保证答案唯一。

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

示例 2：

输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。

示例 3:

输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 */
import java.util.HashMap;
import java.util.Map;
/*
思路很简单，定义左右指针定义一个滑动窗口，然后右指针向右移动，直到满足窗口子串
然后左指针开始向右移动，直到不是窗口子串为止，此时左指针位置-1就是最小窗口子串
 */
class Solution11 {
    public String minWindow(String s, String t) {
        // 记录 t 中每个字符需要的次数
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口内的字符计数
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = -1;          // 窗口左右边界（左闭右闭）
        int minLen = Integer.MAX_VALUE;     // 最短子串长度
        int bestLeft = 0, bestRight = 0;    // 最短子串的起止索引（bestRight 不包含）

        while (right < s.length()) {
            // 右指针右移，扩大窗口
            right++;
            if (right < s.length() && need.containsKey(s.charAt(right))) {
                char c = s.charAt(right);
                window.put(c, window.getOrDefault(c, 0) + 1);
            }

            // 当窗口满足覆盖条件时，尝试收缩左边界
            // isCovered 是自定义方法
            while (isCovered(need, window) && left <= right) {
                // 更新最小长度
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    bestLeft = left;
                    bestRight = left + minLen;  // substring 的结束索引（不包含）
                }

                // 左指针右移前，如果左边字符是需要字符，则从窗口中移除
                if (need.containsKey(s.charAt(left))) {
                    char c = s.charAt(left);
                    window.put(c, window.get(c) - 1);
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(bestLeft, bestRight);
    }

    // 检查当前窗口是否覆盖了 t 中的所有字符
    private boolean isCovered(Map<Character, Integer> need, Map<Character, Integer> window) {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            char c = entry.getKey();
            int required = entry.getValue();
            if (window.getOrDefault(c, 0) < required) {
                return false;
            }
        }
        return true;
    }
}
