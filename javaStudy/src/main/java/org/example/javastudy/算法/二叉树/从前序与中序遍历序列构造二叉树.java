package org.example.javastudy.算法.二叉树;
/*
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历
请构造二叉树并返回其根节点。
 */
import java.util.HashMap;
import java.util.Map;
/*
对于任意一颗树而言，前序遍历的形式总是

[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是

[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]

在新的方法 myBuildTree 中，首先要确定传入的 preorder 数组的左边界小于右边界，不然就返回 null
然后要确定当前根节点的位置，由前序遍历的性质可知，传入的 preorder 数组的左边界一定是其当前根节点
根据当前根节点，就可以利用 哈希表确认 inorder 数组当前根节点的位置（哈希表添加数据时数值作为键，索引作为值就是为了根据数值查找索引）
然后就可以建立 TreeNode 数据作为根节点，
计算出当前根节点左子树的长度，然后进行递归，获取当前根节点的左子树
递归时 preorder 数组当前根节点左子树的左边界为原来的左边界向右一格，右边界为当前左子树的左边界+当前根节点左子树的长度
inorder 数组当前根节点左子树的左边界为原左边界，右边界为根节点-1
preorder 数组当前根节点右子树的左边界为当前左子树的左边界+当前根节点左子树的长度+1，右边界为原来的右边界
inorder 数组当前根节点右子树的左边界为当前根节点+1，右边界为原来的右边界
 */
class Solution3 {
    private Map<Integer,Integer> indexMap;
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left,int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int preorder_root = preorder_left;
        int inorder_root = indexMap.get(preorder[preorder_root]);
        int preorder_left_buildtree = inorder_root - inorder_left;
        TreeNode root = new TreeNode(preorder[preorder_root]);
        root.left = myBuildTree(preorder,inorder,preorder_left+1,preorder_left+preorder_left_buildtree,inorder_left,inorder_root-1);
        root.right = myBuildTree(preorder,inorder,preorder_left+preorder_left_buildtree+1,preorder_right,inorder_root+1,inorder_right);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<Integer,Integer>();
        int n = inorder.length;
        for (int i = 0;i < n; i++) {
            indexMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,n-1,0,n-1);
    }
}