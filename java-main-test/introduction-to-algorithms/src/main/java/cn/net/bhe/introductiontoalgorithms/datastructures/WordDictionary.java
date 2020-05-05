package cn.net.bhe.introductiontoalgorithms.datastructures;

/**
 * 设计一个支持以下两种操作的数据结构：
 * void addWord(word)
 * bool search(word)
 * 
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 '.'或 a-z。'.'可以表示任何一个字母。
 * 示例:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 
 * 说明:
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 * 
 * 此题使用 前缀树 + 回溯 解法
 * @author Administrator
 *
 */
public class WordDictionary {
    ListNode root = new ListNode();
    class ListNode {
        ListNode[] children;
        boolean isWord;
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("a");
        dict.addWord("ab");
        System.out.println(dict.search("a"));
        System.out.println(dict.search("a."));
        System.out.println(dict.search("ab"));
        System.out.println(dict.search(".a"));
        System.out.println(dict.search(".b"));
        System.out.println(dict.search("ab."));
        System.out.println(dict.search("."));
        System.out.println(dict.search(".."));
    }
    
    public void addWord(String word) {
        ListNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children == null) node.children = new ListNode[26];
            if (node.children[i] == null) {
                node.children[i] = new ListNode();
            } 
            node = node.children[i];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return searchDo(word, 0, root);
    }
    
    public boolean searchDo(String word, int i, ListNode node) {
        if (i > word.length() - 1) {
            if (node.isWord) return true;
            return false;
        } else {
            if (node == null || node.children == null)
            return false;
        }
        char c = word.charAt(i);
        if (c == '.' ) {
            for (ListNode child : node.children) {
                if (child != null) {
                    if (searchDo(word, i + 1, child)) return true;
                }
            }
            return false;
        }
        int x = c - 'a';
        if (node.children[x] != null) {
            return searchDo(word, i + 1, node.children[x]);
        }
        return false;
    }
    
}
