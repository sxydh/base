package cn.net.bhe.basics.io;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Java5的新特征，我们可以通过 Scanner 类来获取用户的输入。
 */
public class ScannerTest {
    
    static Scanner input = new Scanner(System.in);
    
    /**
     * 只输出下一个分隔符前的字符串
     */
    @Test
    public void next() {
        String next = input.next();
        System.out.println(next);
        input.close();
    }
    
    /**
     * 输出整行
     */
    @Test
    public void nextLine() {
        String nextLine = input.nextLine();
        System.out.println(nextLine);
        input.close();
    }

}
