package org.example.javastudy.算法.二叉树;
/*
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
节点的值各不相同
 */
class Solution5 {

    private TreeNode ans;
    public Solution5 () {
        this.ans = null;
    }
    //
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        // lson && rson 代表当前节点的左子树和右子树各有一个指定节点，也就是说当前节点是一个祖先节点
        // 以或连接表示有一个成立即可
        // root.val == p.val || root.val == q.val 代表当前节点是指定节点之中的一个，因为树中节点没有重复值
        // lson || rson 代表当前节点的子树中有至少一个含义指定节点
        // 以和连接表示当前节点为指定节点的一个，且有一个子树中含有指定节点，也就是说，当前节点为祖先节点
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        // 代表当前节点的左子树或右子树含有至少一个指定节点或当前节点为指定节点
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root,p,q);
        return this.ans;
    }
}
