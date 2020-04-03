package cn.net.bhe.introductiontoalgorithms.common;

import java.util.Arrays;

/**
 * 判断是否是异位词
 */
public class IsAnagram {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * 排序再比较
     * 
     * @param s
     * @param t
     * @return
     */
    public static boolean sortedAndCompare(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = s.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        return Arrays.equals(sa, ta);
    }
    
    /**
     * 相消法
     * 
     * @param s
     * @param t
     * @return
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
