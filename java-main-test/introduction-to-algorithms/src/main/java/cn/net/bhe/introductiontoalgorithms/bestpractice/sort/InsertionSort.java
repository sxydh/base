package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

import java.util.Arrays;

/**
 * 插入排序
 *
 * 概述：一般也被称为直接插入排序。
 * 对于少量元素的排序，它是一个有效的算法。插入排序是一种最简单的排序方法，它的基本思想是将一个记录插入到
 * 已经排好序的有序表中，从而一个新的、记录数增1的有序表。在其实现过程使用双层循环，外层循环对除了第一个元
 * 素之外的所有元素，内层循环对当前元素前面有序表进行待插入位置查找，并进行移动。
 * 
 * 复杂度分析：
 * 在插入排序中，当待排序数组是有序时，是最优的情况，只需当前数跟前一个数比较一下就可以了，这时一共需要比
 * 较 N- 1次，时间复杂度为O(N)。
 * 
 * 最坏的情况是待排序数组是逆序的，此时需要比较次数最多，总次数记为：1+2+3+…+N-1，所以，插入排序最坏情况
 * 下的时间复杂度为O(N^2)。
 * 
 * 复杂度：时间复杂度O(N^2)，空间复杂度O(1)。
 * 
 * 稳定性：就和冒泡排序一样，插入排序采用的策略是不断的交换两个元素。交换不同的元素也意味着而从不交换相同
 * 的元素。只要不交换相同的元素，不可能是不稳定的排序。
 * 
 * 应用场景：在数组已经较为有序，或仅有几个元素的位置有错误的情况下，使用插入排序的效率非常高。因为在插入
 * 排序中，如果一个元素已经处于排好序的位置，那么这个元素可以不做任何交换。而在选择排序中，每一趟排序的结
 * 果和原数组的位置无关，无论是否排好序，选择排序都要进行同样的操作，这样就带来了不必要的时间浪费。
 */
public class InsertionSort {

    /*
     * 第2个元素在左边元素中找到合适位置并插入
     * 第3个...
     * 第n个元素在左边元素中找到合适位置并插入
     */
    @Test
    public void insertionSort() {
        int[] arr = { 0, 2415, 21, 0, 125 };
        insertionSort(arr);
        pln(Arrays.toString(arr));
    }

    public void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 将arr[i]插入到a[i-1], a[i-2], a[i-3] ... 之中
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

}
