package cn.net.bhe.introductiontoalgorithms.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和为0的所有不重合组合。
 */
public class FourSum {
    
    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 4) return ans;
        int maxVal = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4];
        int minVal = nums[0] + nums[1] + nums[2] + nums[3];
        if (maxVal < target || minVal > target) return ans;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int threeSum = target - nums[i];
            minVal = nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (minVal > threeSum) return ans;
            maxVal = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
            if (maxVal < threeSum) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                minVal = nums[j] + nums[j + 1] + nums[j + 2];
//                if (minVal > threeSum) return ans;
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[j] + nums[l] + nums[r];
                    if (sum > threeSum) {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                        continue;
                    } else if (sum < threeSum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        l++;
                        continue;
                    }
                    ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    r--;
                    l++;
                }
            }
        }
        return ans;
    }

}
