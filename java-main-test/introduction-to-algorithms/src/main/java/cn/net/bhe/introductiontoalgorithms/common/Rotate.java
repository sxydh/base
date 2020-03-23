package cn.net.bhe.introductiontoalgorithms.common;

/**
 * 移动数组
 */
public class Rotate {
    
    public static void main(String[] args) {
        replace(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, 5);
    }
    
    /**
     * 使用额外的数组
     */
    public static void copy(int[] nums, int k) {
        k %= nums.length;
        if (k == 0 || nums == null || nums.length <= 1) return;
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % temp.length] = nums[i];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[i] = temp[i];
        }
    }
    
    /**
     * 环状替换<br/>
     * 证明：<br/>
     * 1、总的移动次数count = nums.length<br/>
     * 2、每个循环相交时一定时首尾相接，也就是每个循环没有重复移动元素<br/>
     * 3、所有循环的移动次数累计为n时，一定移完了，否则与1矛盾
     */
    public static void replace(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
    
    /**
     * 反转
     */
    public static void reverse(int[] nums, int k) {
        k %= nums.length;
        doReverse(nums, 0, nums.length - 1);
        doReverse(nums, 0, k - 1);
        doReverse(nums, k, nums.length - 1);
    }
    
    private static void doReverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
