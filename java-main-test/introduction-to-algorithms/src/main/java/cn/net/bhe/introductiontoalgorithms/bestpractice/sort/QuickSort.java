package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 快速排序
 * 
 * 概述：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按
 * 此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 
 * 复杂度分析： 理想的情况是，每次划分所选择的中间数恰好将当前序列几乎等分，经过log2n趟划分，便可得到长度为1的子表。这样，
 * 整个算法的时间复杂度为O(nlog2n) 最坏的情况是，每次所选的中间数是当前序列中的最大或最小元素，这使得每次划分所得的子表中一个为空表，另一子表
 * 的长度为原表的长度-1。这样，长度为n的数据表的快速排序需要经过n趟划分，使得整个排序算法的时间复杂度为O(n^2)。
 * 
 * 可以证明，快速排序的平均时间复杂度是O(nlog2n)。因此，该排序方法被认为是目前最好的一种内部排序方法。
 * 
 * 从空间性能上看，尽管快速排序只需要一个元素的辅助空间，但快速排序需要一个栈空间来实现递归。最好的情况下，即
 * 快速排序的每一趟排序都将元素序列均匀地分割成长度相近的两个子表，所需栈的最大深度为log2(n+1)；但最坏的情况
 * 下，栈的最大深度为n。这样，快速排序的空间复杂度为O(log2n)。
 * 
 * 复杂度：时间复杂度O(nlogn)，空间复杂度O(logn)。
 * 
 * 稳定性：快速排序是不稳定的。原因是快速排序需要维护前后两个索引，在索引发生对换的情况时，很有可能改变了某两
 * 个相同元素的位置，这种跳跃性的交换是肯定会导致不稳定的。
 * 
 * 应用场景：快速排序是我们最为常用的排序方法，上述例子是未经过优化的一种简单快排。实际上，我们通常认为在这些
 * O(nlogn)复杂度的排序方法中，快速排序的效果是最好的。一般的sort函数排序使用的都是快速排序。
 */
public class QuickSort {

    /*
     * 基本思路： 将数组第一个元素作为pivot，通过一次遍历将pivot放置到正确位置，即左边元素 < pivot < 右边元素，然后再
     * 递归地对左右两部分进行同样的操作。
     */
    @Test
    public void quickSort() {
        int[] arr = { 9, 4, 3, 1111, 0, 2415, 21, 0, 125 };
        quickSort(arr, 0, arr.length - 1);
        pln(Arrays.toString(arr));
    }

    void quickSort(int arr[], int low, int high) {
        if (low >= high) {
            return;
        }

        int left = low + 1;
        int right = high;
        int pivot = low;

        while (true) {
            while (arr[left] <= arr[pivot] && left < high) {
                left++;
            }
            while (arr[right] >= arr[pivot] && right > low) {
                right--;
            }
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            } else {
                break;
            }
        }

        int temp = arr[pivot];
        arr[pivot] = arr[right];
        arr[right] = temp;

        pivot = right;
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);
    }

}
