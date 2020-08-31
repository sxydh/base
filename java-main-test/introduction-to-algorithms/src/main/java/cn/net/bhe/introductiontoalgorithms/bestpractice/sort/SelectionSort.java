package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 选择排序
 *
 * 概述：选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。首先在未排序序列中找到最小（大）
 * 元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的 末尾。以此类推，直到所有元素均排序完毕。
 * 
 * 复杂度分析：
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。选择排序每次交换一
 * 对元素，它们当中至少有一个将被移到其最终位置上，因此对n个元素的表进行排序总共进行至多n-1次交换。在所有
 * 的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 * 
 * 选择排序的交换操作介于0和(n-1)次之间。选择排序的比较操作为n(n-1)/2次。选择排序的赋值操作介于0和3(n-1) 次之间。
 * 比较次数O(n^2)，比较次数与关键字的初始状态无关，总的比较次数N=(n-1)+(n-2)+...+1=n(n-1)/2。交换
 * 次数O(n)，最好情况是，已经有序，交换0次；最坏情况是，逆序，交换n-1次。交换次数比冒泡排序较少，由于交换
 * 所需CPU时间比比较所需的CPU时间多，n值较小时，选择排序比冒泡排序快。
 * 
 * 复杂度：时间复杂度O(n^2)，空间复杂度O(1)。
 * 
 * 稳定性：选择排序是将数组中较小的元素以此放到最前面的排序方法，这种方法按照次序将元素前置，如果序列
 * 2 2 1，那么我们需要将第一个2和1交换，交换之后两个2的位置发生了变化，所以是不稳定的。
 * 
 * 应用场景：总的来说，作为一种初级排序算法，选择排序并不能和其他快速的排序作比较，实践意义也比较少。但
 * 是选择排序有两个鲜明的优点：运行时间和数组排序无关，选择次数最少。
 * 原地操作几乎是选择排序的唯一优点，当空间复杂度要求较高时，可以考虑选择排序；实际适用的场合非常罕见。
 */
public class SelectionSort {

    /*
     * 选取[0, end]内最小元素，放置到0位置
     * 选取[1, end]内最小元素，放置到1位置
     * 选取[2, end]内最小元素，放置到2位置
     * ...
     * 有序
     */
    @Test
    public void selectionSort() {
        int[] arr = { 0, 2415, 21, 0, 125 };
        selectionSort(arr);
        pln(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

}
