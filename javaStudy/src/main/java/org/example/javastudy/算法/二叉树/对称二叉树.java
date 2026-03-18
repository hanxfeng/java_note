package org.example.javastudy.算法.二叉树;
/*
给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
/*
使用递归即可，检查它左子节点是否等于它的右子节点即可
 */
class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p ==null || q == null) {
            return false;
        }
        return p.val == q.val && check(q.right, p.left) && check(q.left, p.right);
    }
}
