package cn.net.bhe.introductiontoalgorithms.bestpractice.parse;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import org.junit.jupiter.api.Test;

/**
 * 阿拉伯数字转罗马数字
 */
public class IntToRoman {

    @Test
    public void intToRoman() {
        int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romans = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        pln(intToRoman(10, nums, romans));
    }
    
    public String intToRoman(int num, int[] nums, String[] romans) {
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
