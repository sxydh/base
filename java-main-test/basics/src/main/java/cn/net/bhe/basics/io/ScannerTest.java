package cn.net.bhe.basics.io;

import static cn.net.bhe.utils.main.PrintUtils.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Java5的新特征，我们可以通过 Scanner 类来获取用户的输入。
 * 默认情况下分隔符是空格符。
 */
public class ScannerTest {
    
    /*
     * next()
     * 
     * 输出下一个分隔符前的字符串。
     * 没有字符串会阻塞。
     * 
     * 注意：测试时下一次输入前先清空控制台
     */
    @Test
    public void next() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.next();
            pln(str);
        }
    }
    
    /*
     * hasNext()
     * 
     * 判断是否有下一个输入，有则返回true，否则阻塞。
     */
    @Test
    public void hasNext() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            pln(true);
        }
        scanner.close();
    }
    
    /*
     * nextLine()
     * 
     * 输出整行。
     * 没有字符串则输出空行，不会阻塞。
     */
    @Test
    public void nextLine() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            pln(line.length() == 0 ? "empty" : line);
        }
    }
    
    /*
     * hasNextLine
     * 
     * 判断是否有下一行(空行算一行)，有则返回true，否则阻塞。
     */
    @Test
    public void hasNextLine() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            pln(true);
        }
        scanner.close();
    }

}
