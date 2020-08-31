package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import org.junit.jupiter.api.Test;

/**
 * 阶乘末尾零的个数
 */
public class TrailingZeroes {
    
    /*
     * ret = n!中因子5和2共同出现的次数，即ret = Math.min(cnt5, cnt2)，所以只统计因子5的个数即可
     * 
     * n!包含因子5的部分可写为：
     * (5*1) * (5*2) * ... * (5*k1)，其中(n - (5*k1)) < 5
     * 第一次统计因子5的个数，ret += k1，k1 = (5*k1)/5 = n/5
     * 剩下还包含因子5的部分可写为
     * (5*5*1) * (5*5*2) * ... * (5*5*k2)，其中(n - (5*5*k2)) < 5²
     * 第二次统计因子5的个数，ret += k2，k2 = (5*5*k2)/5² = n/5²
     * 依此类推直到n/5ⁿ = 0
     */
    @Test
    public void binarySearch() {
        pln(trailingZeroes(9));
    }
    
    public int trailingZeroes(int n) {
        int ret = 0;
        while (n != 0) {
            ret += (n /= 5);
        }
        return ret;
    }
    
}
