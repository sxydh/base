package cn.net.bhe.introductiontoalgorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *  ["aa","b"],
 *  ["a","a","b"]
 * ]
 * @author Administrator
 *
 */
public class Partition {

	public static void main(String[] args) {
		System.out.print(new Partition().partition("efe"));
	}
	
    public List<List<String>> partition(String s) {
    	List<List<String>> ans = new ArrayList<List<String>>();
    	partitionDo(ans, new ArrayList<String>(), s, 0);
    	return ans;
    }
    
    public void partitionDo(List<List<String>> ans, List<String> cur,  String s, int start) {
    	if (start > s.length() - 1) {
    		ans.add(new ArrayList<String>(cur));
    	}
    	for (int i = start; i < s.length(); i++) {
    		if (!isPalindrome(s, start, i)) continue;
    		cur.add(s.substring(start, i + 1));
    		partitionDo(ans, cur, s, i + 1);
    		cur.remove(cur.size() - 1);
    	}
    }
    
    public static boolean isPalindrome(String s, int start, int end) {
    	for (; start < end && start != end; start++, end--) {
    		if (s.charAt(start) != s.charAt(end)) return false;
    	}
    	return true;
    }
	
}
