package org.example.javastudy.算法.链表;
/*
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
 */

/*
使用迭代的方式进行，代码很简单，很容易看明白，主要是链表的变化比较难想，有注释进行说明
 */
class Solution11 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0,head);
        ListNode temp = dummyHead;

        while (temp.next != null && temp.next.next != null) {
            ListNode n1 = temp.next;
            ListNode n2 = temp.next.next;
            temp.next = n2;
            // 此时 t 和 n1 同时指向 n2,n2指向 n3
            n1.next = n2.next;
            // 此时，t 指向 n2，n1 和 n2 同时指向 n3
            n2.next = n1;
            // 此时，t -> n2 -> n1 -> n3
            temp = n1;
            // 此时，将中间指针 temp 从原来的位置移动到 n1 的位置，为新一轮循环做准备
        }
        return dummyHead.next;
    }
}