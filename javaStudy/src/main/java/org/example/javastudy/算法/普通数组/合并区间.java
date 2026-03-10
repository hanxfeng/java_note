package org.example.javastudy.算法.普通数组;
/*
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2：

输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

示例 3：

输入：intervals = [[4,7],[1,4]]
输出：[[1,7]]
解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
首先对数组中的数组进行排序，依据是第一个元素的大小。
然后将第一个数组加入 merged 数组中，然后按顺序去考虑剩下的每一个数组
如果接下来的数组的左端点在 merged 数组中的最后一个区间的右端点之后，那么它们肯定不重合
将这个数组添加到 merged 数组中
如果在之前，就需要更新 merged 中最后一个区间的右端点，选择两个右端点中较大的一个即可
 */
class Solution14 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 使用匿名内部类重写了 Comparator 方法
        // 在此方法中，如果返回值为负数，则先传入的（interval1）排在前面
        // 如果返回值是正数，则后传入的（interval2）排在前面
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < L) {
                // new int[]{L, R} 作用是创建带有两个整数的数组[L,R]
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        // 作用是创建一个长度与 merged 等长的空数组，然后将 merged 中的数组逐个装到里面
        return merged.toArray(new int[merged.size()][]);
    }
}


