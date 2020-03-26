package cn.net.bhe.introductiontoalgorithms.common;

import java.util.Arrays;
import java.util.List;

/**
 * 判断是否是快乐数<br/>
 * 快乐数（happy number）有以下的特性：在给定的进位制下，该数字所有数位(digits)的平方和，得到的新数再次求所有数位的平方和，如此重复进行，最终结果必为1。
 */
public class IsHappy{

    /**
     * 不是快乐数的数称为不快乐数（unhappy number），所有不快乐数的数位平方和计算，最後都会进入 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 的循环中。
     * 
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        List<Integer> list = Arrays.asList(new Integer[] {4, 16, 37, 58, 89, 145, 42, 20});
        do {
            if (list.contains(n)) {
                return false;
            }
            if (n == 1) {
                return true;
            }
            n = ss(n);
        } while (true);
    }

    public int ss(int val) {
        int ret = 0;
        do {
            ret += Math.pow(val % 10, 2);
        } while ((val /= 10) != 0);
        return ret;
    }
    
}
