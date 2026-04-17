package org.example.javastudy.算法;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;

        bulidMaXHeap(nums, heapSize);
        // 将数组最大值换到数组末尾，然后将最大堆大小-1,再重新排序堆，这样堆顶元素就是第k大
        for (int i = nums.length-1;i>=nums.length-k+1;i--) {
            swap(nums,0,i);
            heapSize--;
            MaXHeap(nums,0,heapSize);
        }
        return nums[0];
    }

    /**
     * 将数组转为最大堆
     * @param a：要排序的数组
     * @param heapSize：最大堆的大小
     */
    public void bulidMaXHeap(int[] a, int heapSize) {
        // 完全二叉树的根节点索引固定为当前数组大小 n/2-1
        for (int i=heapSize/2-1;i>=0;i--) {
            MaXHeap(a,i,heapSize);
        }
    }

    /**
     * 调整非叶子节点，使其符合最大堆特质：非叶子节点大于等于其叶子节点
     * @param a：要处理的最大堆数组
     * @param i：当前非叶子节点索引
     * @param heapSize：最大堆边界
     */
    public void MaXHeap(int[] a, int i, int heapSize) {
        // 对于非叶子节点 i,其左子节点索引为i*2+1,右子节点索引为i*2+2
        int l = i*2+1,
                r = i*2+2,
                largest=i;
        if (l<heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r<heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a,i,largest);
            MaXHeap(a,largest,heapSize);
        }
    }

    /**
     * 用于交换数组中两个数的位置
     * @param a：进行位置交换的数组
     * @param i：进行位置交换的数1
     * @param j：进行位置交换的数2
     */
    public void swap(int[] a, int i,int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}