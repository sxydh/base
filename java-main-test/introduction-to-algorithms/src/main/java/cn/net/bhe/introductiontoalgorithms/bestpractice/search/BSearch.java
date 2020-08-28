package cn.net.bhe.introductiontoalgorithms.bestpractice.search;

import static cn.net.bhe.utils.main.PrintUtils.*;

import org.junit.jupiter.api.Test;

/**
 * 二分查找算法(binary search algorithm)，也称折半搜索算法(half-interval search
 * algorithm)、对数搜索算法 (logarithmic search algorithm)，是一种在有序数组中查
 * 找某一特定元素的搜索算法。
 * 
 * 二分查找算法在情况下的复杂度是对数时间，进行O(log n)次比较操作
 */
public class BSearch {

    int[] sortedArr = { 0, 1, 5, 78, 79, 99, 100, 101 }; // 0, 1, 5, 78, 99, 100, 101

    @Test
    public void bSearch() {
        int val = 6; // 查找值
        boolean exist = false;

        try {
            // 双指针
            int s = 0;
            int e = sortedArr.length - 1;
            while (s <= e) {
                p(1);
                int mid = (e + s) >>> 1;
                if (val == sortedArr[mid] || val == sortedArr[s] || val == sortedArr[e]) {
                    exist = true;
                    return;
                }
                if (val < sortedArr[mid]) {
                    s++;
                    e = mid - 1;
                } else {
                    e--;
                    s = mid + 1;
                }
            }
        } finally {
            pln(exist);
        }
    }

}
