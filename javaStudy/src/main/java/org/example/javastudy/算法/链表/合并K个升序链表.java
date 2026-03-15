package org.example.javastudy.算法.链表;
/*
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]

 */

/*
很简单啊，merge 函数的作用是将数组中的链表从中间分成两部分，在 return 处进行迭代，配合合并两条链表的 mergeTwoLists 函数
就会形成数组中的链表两两开始合并，直到最后合并成一条链表

 */

class Solution13 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r){
            return null;
        }
        int mid = (l + r) / 2;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null  ? a : b;
        }
        ListNode aPt = a, bPt =b;
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (aPt != null && bPt != null) {
            if (aPt.val < bPt.val) {
                tail.next = aPt;
                aPt = aPt.next;
            }
            else {
                tail.next = bPt;
                bPt = bPt.next;
            }
            tail = tail.next;
        }
        tail.next = aPt != null ? aPt : bPt;
        return head.next;
    }
}