package cn.net.bhe.utils.main;

import java.util.Random;
import java.util.Stack;
import java.util.UUID;
import static cn.net.bhe.utils.main.PrintUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MathUtils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(MathUtils.class);

    public static void main(String[] args) {
        pln(infixToPostfix("()*4+(8-7)"));
    }
    
    public static String infixToPostfix(String exp) {
        String result = new String("");
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result += c;
            } 
            else if (c == '(') {
                stack.push(c);
            } 
            else if (c == ')') {
                if (prec(stack.peek()) == -1) {
                    return "Invalid Expression";
                }
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                if (stack.isEmpty() || stack.peek() != '(') {
                    return "Invalid Expression";
                } 
                else {
                    stack.pop();
                }
            } 
            else {
                while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            result += stack.pop();
        }
        return result;
    }

    private static int prec(char ch) {
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

    public static String binaryString(int value) {
        String s = "";
        for (int n = value; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        return s;
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
        }

        else {
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
        String result = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result += random.nextInt(10);
        }
        return result;
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
