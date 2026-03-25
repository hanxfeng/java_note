package org.example.javastudy.算法.二叉树;
/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。


 */
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int l = Math.max(maxGain(node.left), 0);
        int r = Math.max(maxGain(node.right), 0);

        // priceNewpath 是指当前节点作为根节点时最大路径的和
        int priceNewpath = node.val + l + r;
        maxSum = Math.max(maxSum,priceNewpath);
        return node.val + Math.max(l,r);
    }
}
