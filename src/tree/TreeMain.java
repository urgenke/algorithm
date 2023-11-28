package tree;

import base.TreeNode;

/**
 * @author : mocun
 * @since : 2023/11/22
 */
public class TreeMain {

    public boolean isSymmetric(TreeNode root) {
        assert root != null;
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (right == null && left == null) {
            return true;
        }

        if (left == null) {
            return false;
        }

        if (right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }


        boolean b1 = compare(left.right, right.left);
        boolean b2 = compare(left.left, right.right);
        return b1 && b2;
    }


    // 1 2 3 4
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int left = numTrees(i - 1);
            int right = numTrees(n - i);
            count = count + left * right;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new TreeMain().numTrees(3));
    }

}
