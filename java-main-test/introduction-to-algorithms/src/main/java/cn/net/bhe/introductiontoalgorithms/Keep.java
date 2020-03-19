package cn.net.bhe.introductiontoalgorithms;

import java.util.ArrayList;
import java.util.List;

public class Keep {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    
    public static boolean isPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) return true;
        for (int i = 0, j = s.length() - 1; i != j && j > i; i++, j--) {
            if (Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(j))) {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            }
        }
        return true;
    }
    
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}