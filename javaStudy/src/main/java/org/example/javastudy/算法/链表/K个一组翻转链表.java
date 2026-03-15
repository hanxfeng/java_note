package org.example.javastudy.算法.链表;
/*

 */

/*
整体流程

    虚拟头节点 hair 简化操作。

    用 pre 跟踪上一组的末尾。

    每组中，通过 tail 找到组的末尾，并保存下一组起点 nex。

    调用 myReverse 翻转当前组，得到新头 head 和新尾 tail。

    将翻转后的子链表接回：pre.next = head，tail.next = nex。

    更新 pre = tail，head = tail.next 进入下一组。

    最后返回 hair.next。
 */


/*

 */
class Solution12 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0,head);
        ListNode pre = hair;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail =tail.next;
                if(tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = nex;
            head = tail.next;
            pre = tail;


        }
        return hair.next;
    }
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode temp = p.next;
            p.next = prev;
            prev = p;
            p = temp;
        }
        return new ListNode[]{tail,head};
    }
}
