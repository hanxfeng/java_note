package org.example.javastudy.算法.链表;
/*
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
/*
从头开始，将两个链表的值相加，然后用 carry 存放多于此位的数，例如 n1 + n2 = 9 + 9 = 18
carry 的值就是 1,然后向后遍历链表即可
 */
class Solution9 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int addNum = n1 + n2 + carry;
            carry = addNum / 10;
            if (head == null) {
                head = new ListNode(addNum % 10);
                tail = head;
            }
            else {
                tail.next = new ListNode(addNum % 10);
                tail = tail.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
