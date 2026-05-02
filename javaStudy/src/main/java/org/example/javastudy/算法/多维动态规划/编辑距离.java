package org.example.javastudy.算法.多维动态规划;
/*
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
 */

/*
1. 总共有六种操作方式，向 A 中插入，向 B 中插入，在 A 中删除，在 B 中删除，替换 A 中的一个字母，替换 B 中的一个字母
2. 以上六种操作可以等价为三种操作，在 A 中插入，在 A 中删除，在 A 中替换
    在 A 中删除与在 B 中插入等价，例如从 A ： dog 到 B： do，在 A 中删除 g 与在 B 中插入 g 都会使 A 与 B 相等
3. 假设前 i - 1 个字母都处理完毕相同，那么第 i 个字母一定只需要一步就可以处理成 A 与 B 相等
4. 所以就能得到动态规划的状态转移方程，d[i][j] 意为将 A 的前 i 个字母转为 B 的前 j 个字母需要的步数
    D[i][j] =min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1])
    =1+min(D[i][j−1],D[i−1][j],D[i−1][j−1]−1)
5. d[i][j] 有三种转变方式：
    (1) A 的前 i 个字母到 B 的前 j - 1 个字母需要 d[i][j-1] 步，那么到 B 的前 j 个字母需要在 A 中插入一个字母
        就需要 d[i][j-1] + 1 步
    (2) A 的前 i - 1 个字母到 B 的前 j 个字母需要 d[i-1][j] 步，那么从 A 的前 i 个字母到 B 的 j - 1 个字母需要
        从 A 中删除一个字母，需要 d[i-1][j] + 1步
    (3) 从 A 的前 i - 1 个字母到 B 的 j - 1 个字母需要 d[i-1][j-1] 步，如果 i 与 j 相同则不需要替换，只要
        d[i-1][j-1] 步即可，否则需要 d[i-1][j-1]+1 步



 */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // 如果有一个单词为空字符串，那么它们的长度和就是转换需要的步数
        if (m * n == 0) {
            return m + n;
        }

        int[][] d = new int[m+1][n+1];

        // 初始化
        for (int i = 0; i < m + 1; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            d[0][j] = j;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 删除操作使 A 和 B 相同需要的步数
                int left = d[i-1][j] + 1;
                // 插入操作使 A 和 B 相同需要的步数
                int donw = d[i][j-1] + 1;
                // 删除操作使 A 和 B 相同需要的步数
                // DP 数组的下标 i, j 与字符串的下标相差 1
                // 当计算 D[i][j] 时，处理的是 word1 的第 i 个字符，在字符串中的位置为 i - 1
                int left_down = d[i-1][j-1];
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    left_down = d[i-1][j-1] + 1;
                }
                d[i][j] = Math.min(left, Math.min(donw, left_down));
            }
        }
        return d[m][n];
    }
}
