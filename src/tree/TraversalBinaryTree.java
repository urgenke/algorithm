package tree;

import base.TreeNode;

import java.util.*;

/**
 * 二叉树前中后序遍历
 *
 * @author : mocun
 * @since : 2023/11/16
 */
public class TraversalBinaryTree {


    // 类似沿着二叉树外围走一圈
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }


    public void preOrderWithoutRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.pollFirst();
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;

            System.out.println(treeNode.val);

            if (right != null) {
                deque.addFirst(right);
            }
            if (left != null) {
                deque.addFirst(left);
            }
        }
    }


    // 类似将所有节点往下映射到一个坐标系中
    public void middleOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        middleOrder(root.left);
        System.out.print(root.val + " ");
        middleOrder(root.right);
    }

    public void middleOrderWithoutRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.pollFirst();
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;


        }
    }





    // 类似葡萄剪枝
    public void afterOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        afterOrder(root.left);
        afterOrder(root.right);
        System.out.println(root.val);
    }


    // 取巧
    // 前序遍历，但是先访问右子树，最后反转列表
    public void afterOrderWithoutRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.pollFirst();
            TreeNode left = treeNode.left;
            TreeNode right = treeNode.right;

            res.add(treeNode.val);

            if (left != null) {
                deque.addFirst(left);
            }
            if (right != null) {
                deque.addFirst(right);
            }

        }

        Collections.reverse(res);
        System.out.println(res);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6,
                new TreeNode(3, new TreeNode(2), new TreeNode(4))
                , new TreeNode(5, new TreeNode(7), new TreeNode(8))
        );

        new TraversalBinaryTree().preOrderWithoutRecursion(root);

        new TraversalBinaryTree()
                .afterOrder(root);
        new TraversalBinaryTree()
                .afterOrderWithoutRecursion(root);

    }
}
