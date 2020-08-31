package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

import java.util.ArrayList;
import static cn.net.bhe.utils.main.PrintUtils.pln;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 */
public class CombinationSum {

    /*
     * 回溯算法
     */
    @Test
    public void combinationSum(){
        List<List<Integer>> ans = combinationSum(new int[] { 1 }, 1);
        pln(ans);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        combinationSumDo(ans, new ArrayList<Integer>(), candidates, 0, target);
        return ans;
    }

    public static void combinationSumDo(List<List<Integer>> ans, List<Integer> current, int[] candidates, int start, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) { 
            ans.add(new ArrayList<Integer>(current));
            return;
        }
        if (start > candidates.length - 1) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            combinationSumDo(ans, current, candidates, i,  target - candidates[i]);
            current.remove(current.size() - 1);
        }
    }
    
}
