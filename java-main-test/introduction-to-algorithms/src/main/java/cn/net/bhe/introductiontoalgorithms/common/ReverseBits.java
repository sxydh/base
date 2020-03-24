package cn.net.bhe.introductiontoalgorithms.common;

/**
 * 颠倒给定的32位无符号整数的二进制位
 */
public class ReverseBits {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * 取出第n位（n & 1），然后移位到目标位置（<< bitsSize），ans再加上此位的值
     */
    public static int reverseBits(int n) {
        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) {
            ans += (n & 1) << bitsSize;
        }
        return ans;
    }

}
