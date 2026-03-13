package org.example.javastudy.算法.矩阵;
/*
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */

/*
使用辅助数组，定义两个布尔类型的数组，长度分别为行数和列数，遍历数组，假设0左标为（i,j)
那么使row[i]=col[j]=true,遍历结束后进行第二次遍历，将数组中为 true 的部分设置为0
 */
class Solution17 {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}