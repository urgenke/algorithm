package trackback;

import java.util.*;

/**
 * @author : mocun
 * @since : 2023/11/21
 */
public class TrackBackMain {

    //https://leetcode.cn/problems/permutations/?envType=study-plan-v2&envId=selected-coding-interview
    // 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];

        permuteDfs(nums, used, res, path);
        return res;
    }

    private void permuteDfs(int[] nums, boolean[] used, List<List<Integer>> res, Deque<Integer> path) {
        // 递归结束条件，path 数量已经足够
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 已经用过，直接跳过
            if (used[i]) {
                continue;
            }
            path.offerLast(nums[i]);
            used[i] = true;

            permuteDfs(nums, used, res, path);

            path.pollLast();
            used[i] = false;
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);
        permuteUniqueDfs(nums, used, res, path);
        return res;
    }


    private void permuteUniqueDfs(int[] nums, boolean[] used, List<List<Integer>> res, Deque<Integer> path) {
        // 递归结束条件，path 数量已经足够
        if (path.size() == nums.length) {
            // 方法二：判断 res 中是否存在 path 与之相同的情况
            // 能做，但不优雅
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 已经用过，直接跳过
            if (used[i]) {
                continue;
            }

            // 这不是重点
            // 1 1' 2
            // 第二轮 1' 时 跟 1 重复，且之前的没有使用过，下边都不需要看了，直接剪枝
            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }


            path.offerLast(nums[i]);
            used[i] = true;

            permuteUniqueDfs(nums, used, res, path);

            path.pollLast();
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        // 全排列，无重复数字
//        System.out.println(new TrackBackMain().permute(new int[]{1, 2, 3}));

        // 全排列，存在重复数字
        System.out.println(new TrackBackMain().permuteUnique(new int[]{1, 1, 2}));

    }


}
