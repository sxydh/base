package cn.net.bhe.introductiontoalgorithms.bestpractice.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * @author Administrator
 *
 */
public class PermuteUnique {

	public static void main(String[] args) {
		System.out.print(new PermuteUnique().permuteUnique(new int[] {1,1,2}));
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		Arrays.fill(used, false);
		permuteUniqueDo(ans, new ArrayList<Integer>(), nums, used, 0);
		return ans;
	}

	public void permuteUniqueDo(List<List<Integer>> ans, List<Integer> current, int[] nums, boolean[] used, int dept) {
		if (dept > nums.length - 1) {
			ans.add(new ArrayList<Integer>(current));
			return;
		}
		Integer left = null;
		for (int i = 0;; i++) {
			if (i >= nums.length) {
				break;
			}
			if (left != null && nums[i] == left) {
				continue;
			}
			if (used[i]) {
				continue;
			}
			current.add(nums[i]);
			used[i] = true;
			permuteUniqueDo(ans, current, nums, used, dept + 1);
			current.remove(current.size() - 1);
			used[i] = false;
			left = nums[i];
		}
	}
	
}
