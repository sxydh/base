package cn.net.bhe.introductiontoalgorithms.common;

/**
 * 阶乘末尾零的个数
 */
public class TrailingZeroes {
    
    public static void main(String[] args) {
        
    }

    /**
     * ret = n!中因子5和2共同出现的次数，即ret = Math.min(cnt5, cnt2)，所以只统计因子5的个数即可<br/>
     * n!包含因子5的部分可写为：<br/>
     * (5*1) * (5*2) * ... * (5*k1)，其中(n - (5*k1)) < 5<br/>
     * 第一次统计因子5的个数，ret += k1，k1 = (5*k1)/5 = n/5<br/>
     * 剩下还包含因子5的部分可写为<br/>
     * (5*5*1) * (5*5*2) * ... * (5*5*k2)，其中(n - (5*5*k2)) < 5²<br/>
     * 第二次统计因子5的个数，ret += k2，k2 = (5*5*k2)/5² = n/5²<br/>
     * 依此类推直到n/5ⁿ = 0
     */
    public int doTrailingZeroes(int n) {
        int ret = 0;
        while (n != 0) {
            ret += (n /= 5);
        }
        return ret;
    }
    
}
