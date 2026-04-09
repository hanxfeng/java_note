package org.example.javastudy.算法.栈;

import java.util.Collections;
import java.util.LinkedList;

class Solution {
    int ptr;

    public String decodeString(String s) {
        // LinkedList 是双向链表，这里将其作为栈使用
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            // isDigit 用于判断给定字符是否是数字字符
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
                // isLetter 用于判断给定字符是否是字母
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                // String.valueOf 用于将返回的 char 转为字符串
                // ptr++ 是先使用原值进行操作，结束后再使 ptr 的值+1
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                // 用于反转链表
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                // getDigits自定方法会将多位数字字符作为一个字符串返回
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

        public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(v);
        }
        return ret.toString();
    }
}

