package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * 有一只兔子，从出生后第3个月起每个月都生一只兔子，小兔子长到第三个月后每个月又生一只兔子，假如兔子都
 * 不死，问每个月的兔子总数为多少？
 */
public class Fibonacci {

    /*
     * 动态规划
     * 
     * 设nth月的兔子数量y=f(nth)，则一定有
     * f(nth) = f(nth-1) + f(nth-2)
     * 
     * 证明：
     * 令dif = f(nth-1) - f(nth-2)
     * 因为f(nth-2)只兔子在nth月份各自生了一只兔子
     * 而且dif只兔子在nth月份是不可能生兔子的
     * 所以f(nth) = 2f(nth-2) + dif
     * 即f(nth) = f(nth-1) + f(nth-2)
     */
    @Test
    public void dynamicPrograming() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int nth = scanner.nextInt();
            int c = 0;
            if (nth == 1) {
                c = 1;
            } else if (nth == 2) {
                c = 1;
            } else {
                int a = 1;
                int b = 1;
                int i = 3;
                while (i++ <= nth) {
                    c = a + b;
                    a = b;
                    b = c;
                }
            }
            System.out.println(c);
        }
        scanner.close();
    }
    
}
