package dp;

import base.TreeNode;

import java.util.HashMap;

/**
 * @author : mocun
 * @since : 2023/11/29
 */
public class Rob3 {


    HashMap<TreeNode, Integer> mem = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (mem.containsKey(root)) {
            return mem.get(root);
        }

        // 抢
        TreeNode left = root.left;
        TreeNode right = root.right;
        int sit1 = root.val;
        if (left != null) {
            sit1 = sit1 + rob(left.left) + rob(left.right);
        }

        if (right != null) {
            sit1 = sit1 + rob(right.left) + rob(right.right);
        }

        // 不抢
        int sit2 = rob(root.left) + rob(root.right);
        int max = Math.max(sit1, sit2);
        mem.put(root, max);
        return max;
    }


}
