package cn.net.bhe.introductiontoalgorithms.bestpractice.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class Permute {

	public static void main(String[] args) {
		System.out.print(new Permute().permute(new int[] { 1, 1, 2 }));
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		boolean[] used = new boolean[nums.length];
		Arrays.fill(used, false);
		permuteDo(ans, new ArrayList<Integer>(), nums, used, 0);
		return ans;
	}

	public void permuteDo(List<List<Integer>> ans, List<Integer> current, int[] nums, boolean[] used, int dept) {
		if (dept > nums.length - 1) {
			ans.add(new ArrayList<Integer>(current));
			return;
		}
		for (int i = 0;; i++) {
			if (i >= nums.length) {
				break;
			}
			if (used[i]) {
				continue;
			}
			current.add(nums[i]);
			used[i] = true;
			permuteDo(ans, current, nums, used, dept + 1);
			current.remove(current.size() - 1);
			used[i] = false;
		}
	}

}
