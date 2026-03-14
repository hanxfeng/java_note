package org.example.javastudy.算法.链表;
/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */

/*
要返回的是新链表的头节点
新建一个链表，le 指针初始指向第一个值，这个值可以随意
然后创建第二个指针 l3，初始指向链表的第一个值
然后比较 l1 和 l2 的第一个节点的大小，将较小的节点接在新链表后
然后向右移动一格，l3 也向右移动一格，直到 l1 和 l2 有一个值为 null
此时将值不为 null 的指针后续的链表一次接在新链表后即可
最后返回 le.next

新链表的两个指针各有作用
le ：因为 prehead 是我们自己创建的辅助节点，它并不属于原始输入链表
也不是合并后链表的一部分。合并后的第一个真实节点是 prehead.next
l3 ：需要一个指针来跟踪当前合并链表的最后一个节点
以便在每次比较后，将新选出的节点连接到这个最后一个节点后面。

 */
class Solution8 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode le = new ListNode(-1);
        ListNode l3 = le;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                l3.next = list2;
                list2 = list2.next;
            }
            else {
                l3.next = list1;
                list1 = list1.next;
            }
            l3 = l3.next;
        }
        l3.next = list1 == null ? list2 : list1;
        return le.next;
    }
}
