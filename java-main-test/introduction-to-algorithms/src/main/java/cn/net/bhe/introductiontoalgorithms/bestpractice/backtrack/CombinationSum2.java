package cn.net.bhe.introductiontoalgorithms.bestpractice.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * @author Administrator
 *
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.print(new CombinationSum2().combinationSum2(new int[] { 1, 1, 1, 3, 3, 5 }, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        combinationSum2Do(ans, new ArrayList<Integer>(), candidates, 0, target);
        return ans;
    }

    public void combinationSum2Do(List<List<Integer>> ans, List<Integer> current, int[] candidates, int start,
            int target) {
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
        Integer left = null;
        for (int i = start; i < candidates.length; i++) {
            if (left != null && candidates[i] == left) {
                continue;
            }
            current.add(candidates[i]);
            combinationSum2Do(ans, current, candidates, i + 1, target - candidates[i]);
            current.remove(current.size() - 1);
            left = candidates[i];
        }
    }

}
