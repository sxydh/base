package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 堆排序
 * 
 * 概述：利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或
 * 索引总是小于(或者大于)它的父节点。
 * 
 * 稳定性：堆排序类似于选择排序，所以肯定也是一种不稳定的排序方法。堆排序在数组的末端维护有序子数组，这种倒置插
 * 入的方法肯定是一种不稳定的排序方法。
 * 
 * 应用场景：堆排序是一种空间和时间最优的排序方法。但是堆排序也有着一定的局限性，因为堆排序的交换和移动的两个元
 * 素几乎都不是相邻的元素，所以无法利用计算机中的缓存。如果使用堆排序，缓存未命中的次数要远高于大多数比较在相邻
 * 元素间的算法，在大型数组中，这种情况导致时间得不偿失，远不如快速排序，归并排序，甚至是希尔排序等。
 * 
 * 复杂度：堆排序的平均时间复杂度为O(nlogn)，空间复杂度为O(1)。
 */
public class HeapSort {

    /*
     * 堆可以看作一棵完全二叉树，堆的遍历顺序为从左往右，从上至下
     * 大顶堆性质：每个根节点的关键字都大于或等于左右子树的关键字
     * 
     * 基本思路：
     * [0, end]内构造一个大顶堆，此时根节点为[0, end]内最大值，将根节点交换到end
     * [0, end-1]内构造一个大顶堆，此时根节点为[0, end-1]内最大值，将根节点交换到end-1
     * [0, end-2]内构造一个大顶堆，此时根节点为[0, end-2]内最大值，将根节点交换到end-2
     * ...
     * [0, 1]内构造一个大顶堆，此时根节点为[0, 1]内最大值，将根节点交换到1
     * 数组有序
     */
    @Test
    public void heapSort() {
        int[] arr = { 9, 3, 1, 45, 2, 48, 45 };
        heapSort(arr);
        pln(Arrays.toString(arr));
    }

    void heapSort(int[] arr) {
        int n = arr.length;
        // 假设数组构成的堆层数为m
        // 则起始构造堆顶下标i = (n - 2) / 2
        // 且i一定为m-1层最靠右节点的下标，此后从右往左从下往上反推，则可以保证大顶堆性质
        for (int i = (n - 2) / 2; i >= 0; i--) {
            heapAdjust(arr, i, n);
        }
        // 构造子数组的大顶堆并交换
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapAdjust(arr, 0, i);
        }
    }

    /**
     * 构造以 i对应元素 为根节点的子树的大顶堆
     * 
     * @param arr 数组
     * @param i  
     * @param n   数组长度
     */
    void heapAdjust(int[] arr, int i, int n) {
        // arr[]数组构成一个堆，则i的左孩子下标为2 * i + 1
        // 只要2 * i + 1不越界，说明i对应的节点任然是一棵子树，需要继续往下判断，以保证大顶堆性质
        while (2 * i + 1 < n) { 
            // 左孩子下标
            int j = 2 * i + 1;
            
            // 右孩子下标为j + 1
            // 若i存在右孩子且大于左孩子，则只需要和右孩子比较
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j++; // 下标移向右孩子
            }
            // 因为堆排序实现时大顶堆是从右往左从下往上反推，若i对应节点大于等于左右孩子，则一定大于等于左右子
            // 再往下判断
            if (arr[j] <= arr[i]) {
                break;
            }
            // 将大值交换到i位置
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            i = j;
        }
    }

}
