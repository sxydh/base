package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

/**
 * 无重复字符子串的最大长度
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

    }
    
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length <= 1) return length;
        int maxLen = 1;
        for (int headIndex = 0, tailIndex = 1; tailIndex < s.length(); tailIndex++) {
            char tail = s.charAt(tailIndex);
            int newTailIndex = s.indexOf(tail, headIndex);
            if (newTailIndex < tailIndex) { // 出现重复字符
                headIndex = (newTailIndex + 1); // 直接从重复位置后查找新串，因为 (该位置前的无重复串长度) < (当前找到的串的长度)
            }
            int subLen = tailIndex - headIndex + 1;
            maxLen = subLen > maxLen ? subLen : maxLen;
        }
        return maxLen;
    }

}
