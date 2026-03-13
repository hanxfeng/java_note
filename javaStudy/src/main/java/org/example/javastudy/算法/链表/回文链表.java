package org.example.javastudy.算法.链表;
/*
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */

import java.util.ArrayList;
import java.util.List;
/*
思路很简单，把链表中的数保存到数组中，然后使用双指针去对比两边是否相同即可
使用 ans.get(left).equals(ans.get(right)) 而不是直接比较是因为
List<Integer>存储的是Integer对象，而不是基本类型int。当从列表中通过get()方法获取元素时，
返回的是Integer对象。要比较两个Integer对象的值是否相等，应该使用equals()方法，而不是==运算符。
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution3 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> ans = new ArrayList<Integer>();
        ListNode currNode = head;
        while (currNode != null) {
            ans.add(currNode.val);
            currNode = currNode.next;
        }
        int left = 0;
        int right = ans.size() - 1;
        while (left < right) {
            if (!ans.get(left).equals(ans.get(right))) {
                return false;
            }
            left += 1;
            right -= 1;
        }
        return true;
    }
}
