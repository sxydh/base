package cn.net.bhe.introductiontoalgorithms.common;

/**
 * 阿拉伯数字转罗马数字
 */
public class IntToRoman {

    public static void main(String[] args) {
        
    }
    
    int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] romans = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while (i < nums.length) {
            if (num >= nums[i]) {
                ans.append(romans[i]);
                num -= nums[i];
            } else {
                i++;
            }
        }
        return ans.toString();
    }
    
}
