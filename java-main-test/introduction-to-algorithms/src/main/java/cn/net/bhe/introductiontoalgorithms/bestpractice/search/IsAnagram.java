package cn.net.bhe.introductiontoalgorithms.bestpractice.search;

import java.util.Arrays;

/**
 * 两个单词如果包含相同的字母，次序不同，则称为字母易位词(anagram)。
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class IsAnagram {
    
    public static void main(String[] args) {
        
    }
    
    /*
     * 排序再比较
     */
    public static boolean sortedAndCompare(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = s.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        return Arrays.equals(sa, ta);
    }
    
    /*
     * 相消法
     */
    public static boolean offset(String s, String t) {
        int[] array = new int[26];
        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            array[c - 'a']--;
        }
        for (int i : array) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

}
