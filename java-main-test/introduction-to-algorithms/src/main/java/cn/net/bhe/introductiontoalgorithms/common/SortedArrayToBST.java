package cn.net.bhe.introductiontoalgorithms.common;

/**
 * 有序数组转平衡二叉搜索树。<br/>
 * 平衡二叉树：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

    }

    /**
     * 二叉搜索树的中序遍历是一个升序序列，将有序数组作为输入，可以把该问题看做根据中序遍历序列创建二叉搜索树。<br/>
     * 中序遍历：左子树---> 根结点 ---> 右子树。<br/>
     * 高度平衡意味着每次必须选择中间数字作为根节点。
     */
    public static TreeNode inOrderTraversal(int[] nums) {
        return doInOrderTraversal(nums, 0, nums.length - 1);
    }

    public static TreeNode doInOrderTraversal(int[] nums, int left, int right) {
        if (left > right)
            return null;

        // always choose right middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1)
            ++p;

        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = doInOrderTraversal(nums, left, p - 1);
        root.right = doInOrderTraversal(nums, p + 1, right);
        return root;
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
