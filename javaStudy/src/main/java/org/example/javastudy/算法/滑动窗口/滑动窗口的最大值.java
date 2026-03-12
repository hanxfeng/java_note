/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。

 

示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

*/

/*
需要定义一个双端队列，里面储存的是数组的下标
当 i < j 且 nums[i] < nums[j] 时，有：当窗口向右移动时，如果 i 在窗口中，则 j 一定也在窗口中，且 nums[i] 一定不为滑动窗口的最大值
所以，可以将 i 移除出双端队列，解决思路如下：
值：数组中下标对应的值
下标：数组的下标
向队列中添加的都是数组的下标
先将初始窗口的值添加到队列中，一个一个添加，如果后添加的值大于先添加的值，那么将先添加的值删除后再插入后添加的值
这样留下的就是初始窗口中最大值的下标，将最大值添加到数组 ans 中
然后进行循环还使窗口滑动，每循环一次相当于窗口向右一格
循环时先比较要添加的值和队尾值的大小，如果添加的值大则将队尾下标删除后再添加，如果队尾值大，则不进行添加
添加后再检查队头下标是否还在窗口中，如果不再则删除队头下标，直到队头下标在窗口中
此时队头下标是第 i - k + 1 个窗口的最大值，将其保存到 ans[i - k + 1] 中
基于这个性质，可知滑动结束后双端队列中保留下标值从小到大排列，且下标值对应的值大小也从小到大排列
*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
       int n = nums.length;
        // 双端队列，可以在队头和队尾进行插入，删除，获取操作，可以作为栈和队列使用
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            // isEmptty 用于判断当前集合是否为空
            // peekLast 用于获取队列最后的一个元素
              while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                // pollLast 用于移除队尾的最后一个元素
                deque.pollLast();
              }
              // offerLast 用于在队尾插入一个元素
              deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while(deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i-k+1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}