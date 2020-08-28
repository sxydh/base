package cn.net.bhe.introductiontoalgorithms.bestpractice.search;

import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 * 
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 
 * 输入: s = "paper", t = "title"
 * 输出: true
 */
public class IsIsomorphic {

    public static void main(String[] args) {
        
    }
    
    /*
     * 映射
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            Character scmap = map.get(sc);
            if (scmap != null) {
                if (scmap != tc) {
                    return false;
                }
            } else {
                if (map.containsValue(tc)) {
                    return false;
                }
            }
            map.put(sc, tc);
            i++;
        }
        return true;
    }

}
