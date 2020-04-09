package cn.net.bhe.introductiontoalgorithms.common;

import java.util.function.Function;

/**
 * 字符串从头部开始直到遇到无效字符并将其转为数字
 */
public class Atoi {
    
    public static void main(String[] args) {
        System.out.println(new Atoi().fsa("-91283472332"));
    }
    
    static final int start = 0;
    static final int signed = 1;
    static final int in = 2;
    static final int end = 3;
    static final int[][] board = new int[][]{
        {start, signed, in, end},
        {end, end, in, end},
        {end, end, in, end},
        {end, end, end, end}
    };
    int state = 0;
    long ans = 0;
    int symbol = 1;
    
    /**
     * 有限状态机
     * 
     * @param str
     * @return
     */
    public int fsa(String str) {
        Function<Character, Integer> getCol = new Function<Character, Integer>() {
            @Override
            public Integer apply(Character c) {
                if (c == ' ') {
                    return start;
                }
                if (c == '+' || c == '-') {
                    return signed;
                }
                if (Character.isDigit(c)) {
                    return in;
                }
                return end;
            }
        };
        for (char c : str.toCharArray()) {
            state = board[state][getCol.apply(c)];
            if (state == signed && c == '-') {
                symbol = -1;
            } else if (state == in) {
                ans = ans * 10 + (c - '0') * symbol;
                if (symbol == 1 && ans > Integer.MAX_VALUE) {
                    ans = Integer.MAX_VALUE;
                }
                if (symbol == -1 && ans < Integer.MIN_VALUE) {
                    ans = Integer.MIN_VALUE;
                }
            } 
        }
        return (int) ans;
    }
    
    /**
     * 普通方法
     * 
     * @param str
     * @return
     */
    public static int common(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        long ret = 0;
        int symbol = 1;
        char firstc = str.charAt(0);
        if (firstc == '-') {
            symbol = -1;
        } else if (firstc == '+') {
            symbol = 1;
        } else if (!Character.isDigit(firstc)) {
            return 0;
        } else {
            ret += (firstc - '0');
        }
        int i = 1;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            ret = ret * 10 + (str.charAt(i) - '0') * symbol;
            if (symbol == 1 && ret > Integer.MAX_VALUE) {
                ret = Integer.MAX_VALUE;
                break;
            }
            if (symbol == -1 && ret < Integer.MIN_VALUE) {
                ret = Integer.MIN_VALUE;
                break;
            }
            i++;
        }
        return (int) ret;
    }

}
