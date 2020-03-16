package cn.net.bhe.introductiontoalgorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinExchange {

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        int optimal = -1;
        optimal = up2down(coins, amount, new HashMap<>());
        System.out.println(optimal);
    }
    
    /**
     * 自下向上
     */
    static int down2up(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1]; // dp[i]表示达到i用的最少硬币数，默认初始化为0
        for (int i = 1; i <= amount; i++) {
            dp[i] = 999999;
            // 最优方案肯定包含在所有方案内
            // 所有方案  = (任一coin) + (对应子问题的最优解)
            // 遍历所有方案后再取最小值可得答案
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == 999999 ? -1 : dp[amount];
    }
    
    /**
     * 自顶向下
     */
    static int up2down(int[] coins, int amount, Map<Integer, Integer> optimalMap) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1; // 当前问题无解
        }
        if (Arrays.stream(coins).anyMatch(coin -> coin == amount)) {
            optimalMap.put(amount, 1);
            return 1;
        }
        Integer cacheOptimal = optimalMap.get(amount);
        if (cacheOptimal != null) {
            return cacheOptimal;
        }
        Integer currOptimal = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subAmount = amount-coins[i];
            int subOptimal = up2down(coins, subAmount, optimalMap);
            if (subOptimal == -1) {
                continue; // 子问题无解
            }
            currOptimal = Math.min(currOptimal, 1 + subOptimal); // 两个解中的最小值，即最优解
        };
        if (currOptimal == Integer.MAX_VALUE) {
            currOptimal = -1; // 当前问题无解
        }
        optimalMap.put(amount, currOptimal);
        return currOptimal;
    }
    
}
