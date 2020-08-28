package cn.net.bhe.introductiontoalgorithms.bestpractice.search;

/**
 * 众数（出现次数大于数组长度一半）查找
 */
public class MajorityElement {
    
    public static void main(String[] args) {
        
    }
    
    /*
     * 投票算法
     * 
     * 证明：如果候选人不是 maj 则 maj 会和其他非候选人一起反对，所以候选人一定会下台（maj == 0时发生换届选举）。
     */
    public int poll(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1; // 每个人只能投一次票，+1算一票，-1也算一票，然后直接跳到下一个人
        }

        return candidate;
    }
    
    /**
     * 分治策略，如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     */
    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
    
    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }
    
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

}
