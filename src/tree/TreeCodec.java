package tree;

import base.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/">...</a>
 *
 * @author : mocun
 * @since : 2023/11/26
 */
public class TreeCodec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return "";
        }
        queue.add(root);


        while (queue.size() > 0) {
            TreeNode node = queue.pollFirst();
            if (node == null) {
                list.add(-2000);
                continue;
            }
            list.add(node.val);
            TreeNode left = node.left;
            TreeNode right = node.right;
            queue.offerLast(left);
            queue.offerLast(right);
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        List<Integer> list = Arrays.stream(data.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        ArrayDeque<Integer> queue = new ArrayDeque<>(list);
        TreeNode root = new TreeNode(queue.pollFirst());
        ArrayDeque<TreeNode> treeQueue = new ArrayDeque<>();
        treeQueue.offerLast(root);

        TreeNode node;
        while ((node = treeQueue.pollFirst()) != null) {
            Integer left = queue.pollFirst();
            Integer right = queue.pollFirst();
            if (left != null && left!=-2000) {
                node.left = new TreeNode(left);
                treeQueue.offerLast(node.left);
            }

            if (right != null&& right!=-2000) {
                node.right = new TreeNode(right);
                treeQueue.offerLast(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);

        root.left = t1;
        root.right = t2;

        t2.left = t3;
        t2.right = t4;

        System.out.println(new TreeCodec().serialize(root));
        //1,2,3,-2000,-2000,4,5,-2000,-2000,-2000,-2000
        System.out.println(new TreeCodec().deserialize(new TreeCodec().serialize(root)));

    }
}
