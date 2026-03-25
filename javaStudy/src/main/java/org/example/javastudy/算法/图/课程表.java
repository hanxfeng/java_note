package org.example.javastudy.算法.图;

import java.util.ArrayList;
import java.util.List;
/*
将问题转换为图中是否存在环，每个课程是图中的一个节点，一个课程a的前置课程b代表
节点的边由b指向a  b->a,判断是否能完成课程学习，就是判断图中也没有环
如果完成a需要先完成b,完成b要先完成c,完成c要先完成a,那么就会出现
  a -> b -> c -> a
  这样的环
 */

class Solution {
    // 邻接表，存储有向图。edges.get(i) 是一个列表，里面存放所有从课程 i 出发直接指向的课程
    // （即 i 是前置课程，指向的课程是后续课程）
    List<List<Integer>> edges;
    // 用于保存当前节点的状态，0表示未访问，1表示正在访问，2表示已访问
    int[] visited;
    // 表示是否检测到环，如果检测到环就将值改为 false
    boolean valid = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        edges = new ArrayList<List<Integer>>();
        for (int i = 0;i<numCourses;i++) {
            // 为每一个课程创建一个空的邻接列表
            edges.add(new ArrayList<Integer>());
        }
        // 初始化visited数组，每个元素初始为0表示未被访问
        visited = new int[numCourses];
        // 构建图
        for(int[] info : prerequisites) {
            // 遍历所有先修关系[a,b]表示要学a需要先学b,因此将a加入b的邻接表构成 b->a的有向边
            edges.get(info[1]).add(info[0]);
        }
        for(int i = 0; i <numCourses && valid;i++) {
            // 检查每个课程节点，如果没有被访问（值为0）则访问它
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;

    }
    public void dfs(int u) {
        // 将当前节点的状态改为正在访问
        visited[u] = 1;
        // 遍历节点u指向的其他节点
        for (int v :edges.get(u)){
            // 如果后续节点未被访问则递归调用dfs
            if (visited[v]==0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 如果访问时遇到正在被访问的节点，说明存在环，将valid的值改为false
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 遍历结束，将当前节点状态改为已访问
        visited[u] = 2;
    }
}
