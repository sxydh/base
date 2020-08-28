package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 统计小于n的质数的个数
 */
public class CountPrimes {
    
    /*
     * 埃拉托斯特尼筛法
     */
    @Test
    public void countPrimes() {
        System.out.println(countPrimes(10));
    }
    public static int countPrimes(int n) {
        boolean[] bs = new boolean[n + 1];
        Arrays.fill(bs, true);
        for (int i = 2; i * i < n; i++) {
            if (bs[i]) {
                for (int j = i * i; j < n; j += i) {
                    bs[j] = false;
                }
            }
        }
        int ret = 0;
        for (int i = 2; i < n; i++) {
            if (bs[i]) ret++;
        }
        return ret;
    }

}
