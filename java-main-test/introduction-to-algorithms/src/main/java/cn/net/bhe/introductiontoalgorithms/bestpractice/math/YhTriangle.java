package cn.net.bhe.introductiontoalgorithms.bestpractice.math;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * 杨辉三角
 */
public class YhTriangle {

    @Test
    public void yhTriangle() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                triangle[i] = new int[1];
                triangle[i][0] = 1;
                continue;
            }
            int[] preLine = triangle[i - 1];
            triangle[i] = new int[preLine.length + 2];
            for (int j = 0; j < triangle[i].length; j++) {
                int preLinePos = j;
                if (preLinePos < preLine.length) {
                    triangle[i][j] += preLine[preLinePos];
                }
                if (--preLinePos >= 0 && preLinePos < preLine.length) {
                    triangle[i][j] += preLine[preLinePos];
                }
                if (--preLinePos >= 0 && preLinePos < preLine.length) {
                    triangle[i][j] += preLine[preLinePos];
                }
            }
        }
        for (int[] arr : triangle) {
            System.out.println(Arrays.toString(arr));
        }
        scanner.close();
    }
    
}
