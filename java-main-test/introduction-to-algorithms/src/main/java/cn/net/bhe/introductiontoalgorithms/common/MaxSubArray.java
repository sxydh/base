package cn.net.bhe.introductiontoalgorithms.common;

public class MaxSubArray {

    public static
//  void main(String[] args)
    int greedy(int[] nums)
    {
        if (nums.length == 1) return nums[0];
        int cursum = nums[0];
        int maxsum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cursum = Math.max(nums[i], nums[i] + cursum);
            maxsum = Math.max(cursum, maxsum);
        }
        return maxsum;
    }
    
}
