package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

/**
 * 给你n个非负整数a1，a2，...，an，每个数代表坐标中的一个点(i, ai)。
 * 在坐标内画n条垂直线，垂直线i的两个端点分别为(i, ai)和(i, 0)。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 */
public class MaxArea {

    public static void main(String[] args) {
        
    }
    
    /*
     * 双指针法，每次都移动偏矮的指针。
     * 
     * 证明：
     * 令最优解横坐标为[x1, x2]，则y2右边的任意y < y1，且y1左边的任意y < y2。
     * 当指针移动时，[x1, x2]必先遇其一，令其为p'，由上面的推论和指针移动法，在p'移动前，另一指针肯定会
     * 遇到[x1, x2]的另一者，也就是说双指针一定有机会同时经过[x1, x2]。
     */
    public int dpm(int[] height) {
        int maxv = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            maxv = Math.max(maxv, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxv;
    }
    
    /*
     * 暴力法
     */
    public int it(int[] height) {
        int maxv = 0;
        for (int i = 1; i < height.length; i++) {
            int tempv = 0;
            int j = i - 1;
            while (j >= 0) {
                tempv = Math.max(tempv, Math.abs(Math.min(height[i], height[j]) * (i - j)));
                j--;
            }
            maxv = Math.max(tempv, maxv);
        }
        return maxv;
    }
    
}
