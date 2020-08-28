package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

/**
 * 最大子连续和
 */
public class MaxSubArray {

    /*
     * 动态规划
     */
    public static int dynamicProgramming(int[] nums) { // 动态规划
        if (nums.length == 1) return nums[0];
        int maxSumWithRBorder = nums[0]; // 带右边界的最大子连续和
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSumWithRBorder = Math.max(nums[i], nums[i] + maxSumWithRBorder); // 更新边界最大子连和
            maxSum = Math.max(maxSumWithRBorder, maxSum); 
        }
        return maxSum;
    }
    
    /*
     * 分治策略
     */
    public static int divideAndConquer(int[] nums) { // 分治策略
//        int[] nums = { 1, 2 };
        int result = doDivideAndConquer(nums, 0, nums.length - 1);
        return result;
//        System.out.println(result);
    }
    
    static int doDivideAndConquer(int[] nums, int start, int end) {
        if (end == start) {
            return nums[start];
        }
        int middle = (end - start) / 2 + start;
        int lMax = doDivideAndConquer(nums, start, middle);
        int rMax = doDivideAndConquer(nums, middle + 1, end);
        int lMaxWithBorder = lMaxWithBorder(nums, start, middle);
        int rMaxWithBorder = rMaxWithBorder(nums, middle + 1, end);
        int max = Math.max(lMax, rMax);
        return Math.max(max, lMaxWithBorder + rMaxWithBorder);
    }
    
    static int lMaxWithBorder(int[] nums, int start, int end) {
        int sum = nums[end];
        int max = sum;
        int i = end - 1;
        while (i >= start) {
            max = Math.max(max, sum += nums[i]);
            i--;
        }
        return max;
    }
    
    static int rMaxWithBorder(int[] nums, int start, int end) {
        int sum = nums[start];
        int max = sum;
        int i = start + 1;
        while (i <= end) {
            max = Math.max(max, sum += nums[i]);
            i++;
        }
        return max;
    }
    
}
