package cn.net.bhe.introductiontoalgorithms.bestpractice;

/**
 * 求树根
 */
public class AddDigits {
    
    /**
     * 证明：<br/>
     * 设一个四位数<br/>
     * num = (a1 * 1000) + (a2 * 100) + (a3 * 10) + a4 <br/>
     * num = (a1 * 999) + (a2 * 99) + (a3 * 9) + (a1 + a2 + a3 + a4) <br/>
     * num % 9 == (a1 + a2 + a3 + a4) % 9
     * 即一个数对9的余数总是等于各位数之和对9的余数
     * 
     * @param num
     * @return
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

}
