package org.example.javastudy.算法.矩阵;
/*
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */

import java.util.ArrayList;
import java.util.List;

/*
首先定义好 left right up down 四个边界，然后以左上角作为起点，先向右遍历
到达右边界后向下，同时上边界-1,然后到达下边界后向左，同时右边界-1,以此类推
最后数组长度等于长乘宽时，结束循环
 */
class Solution18 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int right = matrix[0].length - 1;
        int left = 0;
        int up = 0;
        int down = matrix.length - 1;
        List<Integer> ans = new ArrayList<>();
        while (true) {
            // 向右
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            up += 1;
            if (ans.size() == matrix.length * matrix[0].length) {
                return ans;
            }
            // 向下
            for (int i = up; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (ans.size() == matrix.length * matrix[0].length) {
                return ans;
            }
            right  -= 1;
            // 向左
            for (int i = right; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            if (ans.size() == matrix.length * matrix[0].length) {
                return ans;
            }
            down -= 1;
            // 向上
            for (int i = down; i >= up; i--) {
                ans.add(matrix[i][left]);
            }
            if (ans.size() == matrix.length * matrix[0].length) {
                return ans;
            }
            left  -= 1;
        }
    }
}
