package cn.net.bhe.introductiontoalgorithms.common;

import java.util.Arrays;

/**
 * 统计小于n的质数的个数
 */
public class CountPrimes {
    
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
    
    /**
     * 埃拉托斯特尼筛法
     * 
     * @param n
     * @return
     */
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
