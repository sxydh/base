package cn.net.bhe.introductiontoalgorithms.common;

public class SingleNumber {

    public static void main(String[] args) {
        
    }
    
    /**
     * 利用异或运算的交换律
     */
    public static int XOR(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
           for (int i = 1; i < nums.length; i++) {
              ans = ans ^ nums[i];
           }
         }
         return ans;
    }

}
