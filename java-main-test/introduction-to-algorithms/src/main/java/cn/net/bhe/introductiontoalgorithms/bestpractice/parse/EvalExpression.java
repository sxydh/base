package cn.net.bhe.introductiontoalgorithms.bestpractice.parse;

import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import cn.net.bhe.utils.main.MathUtils;

import static cn.net.bhe.utils.main.PrintUtils.*;

/**
 * 中序表达式的计算
 * 
 * 思路：
 * 关键是找到运算的入口点，如果从左到右遍历表达式，入口点总是在第一次遇到右括号的位置到对应左括号位置
 * 的区间内。计算完该区间后在向右遍历，遇到右括号后再重复之前的过程。
 * 以上的计算过程可以用后序表达式来表达。
 * 
 * 所以可以先将中序转后序表达式，再计算后序表达式。
 * 
 * 有关后序表达式可以参考前序表达式
 * https://baike.baidu.com/item/%E5%89%8D%E5%BA%8F%E8%A1%A8%E8%BE%BE%E5%BC%8F/1431768#2
 */
public class EvalExpression {
    
    /*
     * 中序转后序
     */
    @Test
    public void infixToPostfix() {
        Scanner scanner = new Scanner(System.in);
        String infix = scanner.nextLine();
        pln(MathUtils.infixToPostfix(infix));
        scanner.close();
    }
    
    /*
     * 计算后序
     */
    @Test
    public void evaluatePostfix() {
        Scanner scanner = new Scanner(System.in);
        String infix = scanner.nextLine();
        List<String> postfix = MathUtils.infixToPostfix(infix);
        pln(MathUtils.evaluatePostfix(postfix));
        scanner.close();
    }
    
}
