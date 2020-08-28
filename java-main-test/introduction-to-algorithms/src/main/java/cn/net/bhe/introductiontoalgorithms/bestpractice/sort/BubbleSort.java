package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

import static cn.net.bhe.utils.main.PrintUtils.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 冒泡排序。又称为泡式排序，是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的
 * 顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个
 * 算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * 
 * 冒泡排序对n个项目需要O(n^2)的比较次数，且可以原地排序。尽管这个算法是最简单了解和实现的排序算法之一，但
 * 它对于包含大量的元素的数列排序是很没有效率的。
 */
public class BubbleSort {

    int[] arr = { 0, 3, 1, 45, 2, 48, 45, 100, 1 };

    @Test
    public void bubbleSort() {
        boolean sorted = false;
        for (int i = 0; !sorted && i < (arr.length - 1); i++) {
            sorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                int f = arr[j];
                int s = arr[j + 1];
                if (f > s) {
                    arr[j] = s;
                    arr[j + 1] = f;
                    sorted = false;
                }
                p(1);
            }
        }
        pln(Arrays.toString(arr));
    }

}
