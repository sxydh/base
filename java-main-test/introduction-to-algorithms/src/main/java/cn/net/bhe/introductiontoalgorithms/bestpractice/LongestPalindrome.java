package cn.net.bhe.introductiontoalgorithms.bestpractice;

import java.util.function.Function;

/**
 * 回文串最大长度
 */
public class LongestPalindrome {
    
    /**
     * 中心扩展法
     */
    public static void 
    main(String[] args) 
//    centerExtend()
    {
        String s = "babad";
        if (s == null || s.length() < 1) {
            System.out.println("");
            return;
        }
        Function<Object, Integer> extend = obj -> {
            Object[] objs = (Object[]) obj;
            String str = (String) objs[0];
            int leftIndex = (Integer) objs[1], rightIndex = (Integer) objs[2];
            while (leftIndex >= 0 && rightIndex < str.length() && str.charAt(leftIndex) == str.charAt(rightIndex)) {
                leftIndex--;
                rightIndex++;
            }
            return rightIndex - leftIndex - 1;
        };
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = (int) extend.apply(new Object[] {s, i, i});
            int len2 = (int) extend.apply(new Object[] {s, i, i + 1});
            int len = Math.max(len1, len2);
            if (end - start < len) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println(s.substring(start, end + 1));
    }
    
    /**
     * Manacher算法
     */
    public static void 
//    main(String[] args)
    manacher()
    {
        String origin = "bbbbacbbcab";
        String newStr = new Function<String, String>() {
            @Override
            public String apply(String s) {
                int n = s.length();
                if (n == 0) {
                    return "^$";
                }
                String ret = "^";
                for (int i = 0; i < n; i++)
                    ret += "#" + s.charAt(i);
                ret += "#$";
                return ret;
            }
        }.apply(origin);
        int length = newStr.length();
        int[] cache = new int[length]; // 存储中心为i的回文串的半径
        int center = 0; // 上一个回文串的中心
        int rBorder = 0; // 上一个回文串的右边界
        for (int i = 1; i < length - 1; i++) {
            int mirror = 2 * center - i; // i关于center的对称位置
            if (i < rBorder) {
                cache[i] = Math.min(rBorder - i, cache[mirror]); // 防止超出边界
            } else {
                cache[i] = 0;
            }
            while (newStr.charAt(i + 1 + cache[i]) == newStr.charAt(i - 1 - cache[i])) {
                cache[i]++;
            }
            
            if (i + cache[i] > rBorder) {
                center = i;
                rBorder = i + cache[i];
            }
        }

        // 找出最大半径和对应的下标
        int max = 0;
        int index = 0;
        for (int i = 1; i < length - 1; i++) {
            if (cache[i] > max) {
                max = cache[i];
                index = i;
            }
        }
        int beginIndex = (index - max) / 2;
        System.out.println(origin.substring(beginIndex, beginIndex + max));
    }
    
}


