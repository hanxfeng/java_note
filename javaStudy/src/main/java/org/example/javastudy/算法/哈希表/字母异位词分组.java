package org.example.javastudy.算法.哈希表;
/*
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]

输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

解释：

在 strs 中没有字符串可以通过重新排列来形成 "bat"。
字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
先创建一个长度为 26 的数组，用于保存一个单词的字母组成
然后将这个数组去除为0的值后组合成一个包含字母和其出现次数的字符串 key，例如：cat --> a1c1t1
然后判断哈希表中是否有和 key 相同的键，如果没有则新建一个空列表保存这个单词，如果有则将这个单词加在已有列表末尾
最后使用 .values 方法将哈希表的值转为数组即可
 */
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 创建哈希表储存结果
        Map<String,List<String>> maps =new HashMap<String,List<String>>();

        for (String str : strs) {
            // 创建计数数组 counts
            int[] counts = new int[26];
            // 统计单词数量
            int length = str.length();
            for (int i = 0;i<length;i++) {
                // charAt 的作用是获取字符串中位置为 i 的字符
                // 在 Unicode/ASCII 编码中，一个字母 - a 就是它在字母表中的位置（0开头）
                // 这行代码的作用是将小写字母映射到数组索引（0 到 25），以便统计每个字母出现的次数。
                counts[str.charAt(i) - 'a'] ++;
            }
            // 创建可变字符串 StringBuffer，这种字符串操作时不会创建新字符串，节省开销
            StringBuffer sb = new StringBuffer();
            // 这个 for 循环的作用是将数组 counts 中不为0的字母添加到字符串中
            // 最终结果示例：cat --> a1c1t1
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char)('a'+i));
                    sb.append(counts[i]);
                }
            }
            // 使用 toString 方法输出最终字符串
            String key = sb.toString();
            // 创建一个新的 List 变量
            // 如果 maps 中不存在键为 key 的变量，则新建一个空 List，否则将存在的 List 赋值给 list
            List<String> list = maps.getOrDefault(key,new ArrayList<String>());
            // 向 list 中添加新单词
            list.add(str);
            // 保存
            maps.put(key,list);
        }
        // 使用 values() 方法将 map 的值转为 List
        return new ArrayList<List<String>>(maps.values());
    }
}
