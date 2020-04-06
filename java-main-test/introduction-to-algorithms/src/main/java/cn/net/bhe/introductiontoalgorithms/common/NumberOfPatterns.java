package cn.net.bhe.introductiontoalgorithms.common;

import java.util.Arrays;

/**
 * 九宫格解锁，至少m点，至多n点，有几种连接方法
 */
public class NumberOfPatterns {
    
    public static void main(String[] args) {
        System.out.println(numberOfPatterns(1, 5));
    }

    public static int numberOfPatterns(int m, int n) {
        int ret = 0;
        boolean[] board = new boolean[9];
        Arrays.fill(board, false);
        while (m <= n) {
            ret += next(board, -1, m, "");
            m++;
        }
        return ret;
    }

    public static int next(boolean[] board, int pre, int remain, String path) {
        if (remain == 0) {
            System.out.println(path);
            return 1;
        }
        int ret = 0;
        for (int i = 0; i < 9; i++) {
            if (!isValid(board, pre, i)) {
                continue;
            }
            board[i] = true;
            ret += next(board, i, remain - 1, path + "," + i);
            board[i] = false;
        }
        return ret;
    }

    public static boolean isValid(boolean[] board, int pre, int cur) {
        if (board[cur])
            return false;
        if (pre == -1)
            return true;
        // 横向相邻、竖向相邻、国际象棋中马的移动
        if ((cur + pre) % 2 == 1)
            return true;
        // 对角线跨一个点
        int mid = (cur + pre) / 2;
        if (mid == 4)
            return board[mid];
        // 对角线相邻
        if ((cur % 3 != pre % 3) && (cur / 3 != pre / 3)) {
            return true;
        }
        return board[mid];
    }
    
}
