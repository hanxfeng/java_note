package org.example.javastudy.算法.堆;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> occu = new HashMap<Integer,Integer>();
        for (int num:nums) {
            occu.put(num,occu.getOrDefault(num,0)+1);
        }

        // java 中的堆，是一种特殊的栈
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });

        // Map.Entry 是一种键值对的数据形式，主要用于配合 Map.entrySet() 进行 Map 数据遍历
        for (Map.Entry<Integer,Integer> entry : occu.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }
            else {
                queue.offer(new int[]{num, count});
            }

        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}