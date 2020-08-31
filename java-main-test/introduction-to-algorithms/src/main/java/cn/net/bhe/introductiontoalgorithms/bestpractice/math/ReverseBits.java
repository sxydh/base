package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

/**
 * 颠倒给定的32位无符号整数的二进制位
 */
public class ReverseBits {
    
    /*
     * 基本思路：利用(n & 1)可得n的二进制最低位
     * 
     * 取出n第低位作为目标值ans的最高位，n右移一位，此时n最低位为原来的次低位
     * 依次类推
     */
    @Test
    public void reverseBits() {
        pln(reverseBits(10));
    }
    
    public static int reverseBits(int n) {
        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) {
            ans += (n & 1) << bitsSize;
        }
        return ans;
    }

}
