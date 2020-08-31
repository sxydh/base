package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 归并排序
 * 
 * 概述：归并排序是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法（Divide and Conquer）的一
 * 个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将
 * 两个有序表合并成一个有序表，称为二路归并。
 * 
 * 稳定性：只要我们在归并的时候记得将前面的子数组放在前面合成，就不会存在不稳定的情况。
 * 
 * 复杂度：时间复杂度O(nlogn)，空间复杂度为：O(n)。
 * 
 * 应用场景：我们经常将归并排序和快速排序进行比较，归并排序在空间复杂度上劣于快速排序，但是较为稳定。在实际的
 * jdk中，复杂的复合类型数组的排序是使用归并排序来处理的，因为对于一个hashcode计算复杂的对象来说，移动的成本
 * 远低于比较的成本。没有差的排序方法，只是在每一种场合下最优解不同。
 */
public class MergeSort {
    
    /*
     * 基本思路：将数组分为左右两部分，对左右部分分别排序，再合并即可
     */
    @Test
    public void mergeSort() {
        int[] arr = { 9, 4, 3, 1111, 0, 2415, 21, 0, 125 };
        mergeSort(arr);
        pln(Arrays.toString(arr));
    }
    
    void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len - 1);
    }

    /*
     * [start, end]区间归并排序
     */
    void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        // 收敛
        if (start >= end) {
            return;
        }
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        // [start1, end1]区间归并排序
        mergeSortRecursive(arr, result, start1, end1);
        // [start2, end2]区间归并排序
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        // 左右合并
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        // 重新赋值，保证[start, end]区间内是有序的
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

}
