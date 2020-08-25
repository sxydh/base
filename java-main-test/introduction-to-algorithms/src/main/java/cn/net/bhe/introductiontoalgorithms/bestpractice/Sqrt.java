package cn.net.bhe.introductiontoalgorithms.bestpractice;

/**
 * 求平方根
 */
public class Sqrt {
    
    public static void main(String args[]) {
        System.out.println(newtonRaphson(2147395599));
    }
    
    /**
     * 牛顿迭代法，理论依据是泰勒展开式
     * @see <a href="https://www.guokr.com/question/461510/">参考</a>
     */
    public static double newtonRaphson(double x) {
        double err = 1e-15;
        double approach = x;
        while (Math.abs(approach - x / approach) > err)
            approach = (x / approach + approach) / 2.0;
        return approach;
    }
    
    /**
     * 递归求解，只保留整数
     * @see <a href="https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode/">参考</a>
     */
    public static int recursion(int x) {
        if (x < 2) return x;
        int left = recursion(x >> 2) << 1;
        int right = left + 1;
        return (long)right * right > x ? left : right;
    }
    
    /**
     * 二分查找，只保留整数
     */
    public static int binarySearch(int x) {
        // 当x>=2时，它的整数平方根一定小于x/2且大于 0，由于a一定是整数，此问题可以转换成在有序整数集中寻找一个特定值，因此可以使用二分查找。
        if (x < 2) return x;
        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
          pivot = left + (right - left) / 2;
          num = (long)pivot * pivot;
          if (num > x) right = pivot - 1;
          else if (num < x) left = pivot + 1;
          else return pivot;
        }
        return right;
    }
    
}
