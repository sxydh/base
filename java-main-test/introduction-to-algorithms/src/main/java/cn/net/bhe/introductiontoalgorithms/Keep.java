package cn.net.bhe.introductiontoalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Keep {

    public static void main(String[] args) {
        System.out.println(new Keep().generateParenthesis(2));
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) return ans;
        doGenerateParenthesis(n, "", ans);
        ans = ans.subList(0, ans.size() - 1);
        return ans;
    }

    public void doGenerateParenthesis(int n, String pre, List<String> ans) {
        if (n == 1) {
            ans.add("()" + pre);
            ans.add("(" + pre + ")");
            ans.add(pre + "()");
            return;
        }
        doGenerateParenthesis(n - 1, "()" + pre, ans);
        doGenerateParenthesis(n - 1, "(" + pre + ")", ans);
        doGenerateParenthesis(n - 1, pre + "()", ans);
    }
    
}
