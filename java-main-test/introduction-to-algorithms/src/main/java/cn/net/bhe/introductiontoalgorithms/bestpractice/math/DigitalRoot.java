package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

/**
 * 求数根
 * 
 * 数根可以通过把一个数的各个位上的数字加起来得到。如果得到的数是一位数，那么这个数就是数根；如果结果是两位
 * 数或者包括更多位的数字，那么再把这些数字加起来。如此进行下去，直到得到是一位数为止。
 * 比如，对于24来说，把2和4相加得到6，由于6是一位数，因此6是24的数根。
 */
public class DigitalRoot {
    
    /*
     * 此方法的证明：
     * 
     * 设一个四位数num1
     * num1 = (a1 * 1000) + (a2 * 100) + (a3 * 10) + a4
     * num1 = (a1 * 999) + (a2 * 99) + (a3 * 9) + (a1 + a2 + a3 + a4)
     * num1 % 9 == (a1 + a2 + a3 + a4) % 9
     * 
     * 令num2 = (a1 + a2 + a3 + a4)
     * 类似上面的过程，有：
     * num2 % 9 = (b1 + ... ) % 9
     *
     * 依此类推，即一个数对9的余数总是等于各位数之和对9的余数
     * 当numx刚好为一位数时，即树根，且：
     * num1 % 9 = numx % 9 = numx
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

}
