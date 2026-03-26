package org.example.javastudy.算法.回溯;
/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*

 */
class Solution2 {
    public List<String> letterCombinations(String digits) {
        List<String> com = new ArrayList<String>();
        if (digits.length() == 0) {
            return com;
        }
        Map<Character, String> phoneMap = new HashMap<Character,String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        back(com,phoneMap,digits,0,new StringBuffer());
        return com;
    }
    public void back(List<String> com,Map<Character,String> phoneMap, String digits, int index, StringBuffer c){
        if (index == digits.length()) {
            com.add(c.toString());
        }
        else{
            char d = digits.charAt(index);
            String l = phoneMap.get(d);
            int lC = l.length();
            for (int i = 0; i < lC; i++) {
                c.append(l.charAt(i));
                back(com,phoneMap,digits,index+1,c);
                c.deleteCharAt(index);
            }
        }
    }

}
