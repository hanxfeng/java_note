package org.example.javastudy.算法.矩阵;
/*
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。

 */
/*
Z字查找法，由矩阵性质可知，如果 （x,y) 大于 t,那么所有的 (x,y+n)。n为任意整数
大于t,如果(x,y) 小于t,那么(x-n,y) 小于t。因此，只需要确认当前数是大于t还是小于t
再逐步缩小范围即可，如果大于t,那么使y-1，如果小于t,那么使x+1
 */
class Solution20 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] < target) {
                x += 1;
            }
            else if (matrix[x][y] > target) {
                y -= 1;
            }
            else if (matrix[x][y] == target) {
                return true;
            }
        }
        return false;
    }
}
