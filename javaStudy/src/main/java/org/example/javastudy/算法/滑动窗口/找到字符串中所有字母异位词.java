package org.example.javastudy.算法.滑动窗口;

/*
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。


示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。

 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
定义两个长度为 26 的数组，sCount pCount,用于保存单词
将 p 中的所有单词添加到 pCount 中，s 的前p个单词添加到 sCount 中
添加方法：共有 26 个字母，索引为0则代表 a,值为，值为 1 则代表出现一次
先判断这时的 pCount 和 sCount 是否相同，如果相同则添加 起始索引 0
然后 sCount 向右滑动一格，即将索引为 0 的字母在 sCount 中删去
然后将索引为 p.length 的字母添加到 Scount 中
再对比pCount 与 sCount 是否相同，
 */
class Solution9 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] sCount = new int[26];
        int[] pCount = new int[26];

        if (s.length() < p.length()) {
            return new ArrayList<Integer>();
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i)-'a'] += 1;
            sCount[s.charAt(i)-'a'] += 1;
        }
        if (Arrays.equals(sCount, pCount)) {
            list.add(0);
        }
        int left = 0;
        int right = p.length();

        while (right < s.length()) {
            sCount[s.charAt(left)-'a'] -= 1;
            sCount[s.charAt(right)-'a'] += 1;
            left += 1;
            right += 1;
            if (Arrays.equals(sCount, pCount)) {
                list.add(left);
            }
        }

        return list;

    }
}

