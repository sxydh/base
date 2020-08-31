package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

import static cn.net.bhe.utils.main.PrintUtils.pln;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParenthesis {
    
    /*
     * 回溯
     */
    @Test
    public void generateParenthesis() {
        List<String> list = generateParenthesis(5);
        pln(list);
    }
    
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
