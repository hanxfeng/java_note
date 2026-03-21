package org.example.javastudy.算法.二叉树;
/*
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
import java.util.HashMap;
import java.util.Map;
/*
首先明确概念：前缀和，前缀和是只从根节点到当前节点中间所有节点的和。
假设当前节点的前缀和为 curr，
则如果有从根节点 root 到当前节点 node 的路径中存在节点 Pi 的前缀和为 curr - targetSum
则节点 Pi+1 到 node 路径上所有节点的和一定为 targetSum

可以使用递归处理这道问题，初始化哈希表，哈希表的键为前缀和，值为前缀和与键相同的节点的数量
然后进行初始处理，向哈希表中添加 0，1 表示根节点
然后向递归函数输入初始数据
在递归函数中，如果传入的节点为 null，则返回 0
初始化变量 ret =，该变量用于记录前缀和为 curr - targetSum 的节点的数量
然后更新当前节点的前缀和 curr，将当前节点前缀和的数据更新至哈希表中
进行递归，求当前节点左右子树中前缀和为 curr - targetSum 从节点数量
最后从哈希表中移除当前节点的前缀和
最后返回 ret

 */
class Solution4 {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long,Integer> prefix = new HashMap<Long,Integer>();
        prefix.put(0L,1);
        return dfs(root,prefix,0,targetSum);
    }
    public int dfs(TreeNode root, Map<Long,Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        curr += root.val;
        ret = prefix.getOrDefault(curr-targetSum,0);
        prefix.put(curr,prefix.getOrDefault(curr,0)+1);

        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);

        return ret;
    }
}
