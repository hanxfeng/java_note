package org.example.javastudy.算法.链表;
/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */

/*
见 https://www.bilibili.com/video/BV13JcSz2Eyh
 */
class Solution2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
