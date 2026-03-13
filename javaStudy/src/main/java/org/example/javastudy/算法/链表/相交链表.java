package org.example.javastudy.算法.链表;
/*
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。

图示两个链表在节点 c1 开始相交：

题目数据 保证 整个链式结构中不存在环。

注意，函数返回结果后，链表必须 保持其原始结构 。
 */
/*
可以使用双指针的方法来进行，只有当两个链表都不为空时两个链表才可能相交，因此需要先判断两个链表为空，空则返回 null
先定义 pA pB 两个新 ListNode（单向链表），它们的值是 headA 和 headB，在这种情况下，它们相当于
两个指针，初始指向两个链表的开头
然后进行循环，结束条件为两个指针指向的值相等，每次需要同时更新 pA 和 pB，如果它们不为空（未到达链表末尾）则使它们向右一格
否则将其移动到另一个链表的头节点
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? pA = headB : pA.next;
            pB = pB == null ? pB = headA : pB.next;
        }
        return pA;
    }
}
