package org.example.javastudy.算法.链表;
/*
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
import java.util.Deque;
import java.util.LinkedList;
/*
使用栈来进行处理，先将链表元素入栈，然后再弹出n个，此时栈中最后一个
链表节点的下一个节点就是要删除的节点，此时再将最后一个节点的next设置为next.next即可
 */
class Solution10 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        Deque<ListNode> deque = new LinkedList<ListNode>();
        ListNode cur = dummy;

        while (cur != null) {
            deque.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            deque.pop();
        }

        ListNode prev = deque.peek();
        prev.next = prev.next.next;
        return dummy.next;
    }
}
