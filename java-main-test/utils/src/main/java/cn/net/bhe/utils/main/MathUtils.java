package cn.net.bhe.utils.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.net.bhe.utils.main.PrintUtils.*;

public class MathUtils {
    static Logger log = LoggerFactory.getLogger(MathUtils.class);
    
    @Test
    public void evaluatePostfix() {
        Scanner scanner = new Scanner(System.in);
        String infix = scanner.nextLine();
        List<String> postfix = infixToPostfix(infix);
        pln(evaluatePostfix(postfix));
        scanner.close();
    }
    
    /**
     * 计算后序表达式
     * 
     * @param postfix       表达式
     * @return
     */
    public static int evaluatePostfix(List<String> postfix) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < postfix.size(); i++) {
            String ele = postfix.get(i);
            int n1;
            int n2;
            switch (ele) {
                case "*":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n1 * n2);
                    break;
                case "/":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 / n1);
                    break;
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n2 - n1);
                    break;
                default:
                    stack.push(Integer.parseInt(ele));
            }
        }
        return stack.pop();
    }
    
    @Test
    public void infixToPostfix() {
        Scanner scanner = new Scanner(System.in);
        String infix = scanner.nextLine();
        pln(infixToPostfix(infix));
        scanner.close();
    }
    
    /**
     * 中序转后序表达式
     * 
     * @param infix 中序表达式
     * @return
     */
    public static List<String> infixToPostfix(String infix) {
        List<String> postfix = new ArrayList<>();
        Stack<Character> optStack = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            // 数值的处理：以i为起点向后查找有效数字，直接放入后序表达式
            if (isNum(c)) {
                int start = i;
                if (i == infix.length() - 1) {
                    i++;
                } else {
                    while (isNum(infix.charAt(++i))) {
                    }
                }
                postfix.add(infix.substring(start, i));
                i--;
            }
            // 括号的处理：左括号直接入操作符栈
            else if (c == '(' || c == '[' || c == '{') {
                optStack.push(c);
            }
            // 括号的处理：遇到右括号则将该括号内的操作符放入后序表达式中
            else if (c == ')' || c == ']' || c == '}') {
                while (optStack.peek() != '(' && optStack.peek() != '[' && optStack.peek() != '{') {
                    postfix.add(String.valueOf(optStack.pop()));
                }
                optStack.pop();
            }
            // 操作符的处理：减
            else if (c == '-') {
                // 是减号的情况包括：前后都是数字、前面是右括号、后面是左括号
                if ((i != 0 && (isNum(infix.charAt(i - 1)) && isNum(infix.charAt(i + 1)))) || 
                    (infix.charAt(i - 1) == ')' || infix.charAt(i - 1) == ']' || infix.charAt(i - 1) == '}') ||
                    (infix.charAt(i + 1) == '(' || infix.charAt(i + 1) == '[') || infix.charAt(i + 1) == '{') { 
                    while (!optStack.isEmpty() && optPrec(c) <= optPrec(optStack.peek())) {
                        postfix.add(String.valueOf(optStack.pop()));
                    }
                    optStack.push(c);
                }
                // 负号
                else {
                    int start = i;
                    while (isNum(infix.charAt(++i))) {
                    }
                    postfix.add(infix.substring(start, i));
                    i--;
                }
            }
            // 操作符的处理：加
            else if (c == '+') {
                while (!optStack.isEmpty() && optPrec(c) <= optPrec(optStack.peek())) {
                    postfix.add(String.valueOf(optStack.pop()));
                }
                optStack.push(c);
            }
            // 操作符的处理：乘除
            else if (c == '*' || c == '/') {
                while (!optStack.isEmpty() && optPrec(c) <= optPrec(optStack.peek())) {
                    postfix.add(String.valueOf(optStack.pop()));
                }
                optStack.push(c);
            }
        }
        while (!optStack.isEmpty()) {
            postfix.add(String.valueOf(optStack.pop()));
        }
        return postfix;
    }
    
    private static int optPrec(char ch) {
        switch (ch) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
        }
        return -1;
    }
    
    public static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static String binaryString(int value) {
        return Integer.toBinaryString(value);
    }

    public static int gcd(int p, int q) {
        if (p < 0 || q < 0) {
            throw new IllegalArgumentException("args must be nonnegative integer");
        }
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static int primeNum(int min) {
        if (min < 2) {
            throw new IllegalArgumentException();
        }
        for (; min >= 2; min--) {
            if (isPrime(min)) {
                return min;
            }
        }
        return 2;
    }

    public static boolean isPrime(int value) {
        if (value < 5) {
            if (value == 2 || value == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            int r = value % 6;
            if (r != 1 && r != 5) {
                return false;
            }
            for (int i = 2; i * i <= value; i++) {
                if (value % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String randomNum(int length) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public static double uniform(double a, double b) {
        return a + Math.random() * (b - a);
    }

    public static int uniform(int n) {
        return (int) (Math.random() * n);
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

}
