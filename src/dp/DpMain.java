package dp;

import java.util.Arrays;

/**
 * @author : mocun
 * @since : 2023/11/27
 */
public class DpMain {

    public int[] runningSum(int[] nums) {
        if (nums.length == 0) {
            return new int[]{};
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }


    public int minPathSum(int[][] grid) {
        int i = grid.length;
        int j = grid[0].length;
        int[][] dp = new int[i][j];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        minPathSumDfs(grid, dp, 0, 0);
        return dp[0][0];
    }

    public int minPathSumDfs(int[][] grid, int[][] dp, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        // end
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            dp[i][j] = grid[i][j];
            return grid[i][j];
        }

        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }

        // right
        int right = minPathSumDfs(grid, dp, i, j + 1);
        // down
        int down = minPathSumDfs(grid, dp, i + 1, j);
        int min = Math.min(right, down);
        int path = min + grid[i][j];
        dp[i][j] = path;
        return path;
    }


    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

//
//    /**
//     * 输入：[7,1,5,3,6,4]
//     * 输出：5
//     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//     */
//    public int maxProfit(int[] prices) {
//
//
//    }


    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return robDfs(nums, dp, 0);
    }

    public int robDfs(int[] nums, int[] dp, int index) {
        if (index >= nums.length) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        if (index == nums.length - 1) {
            dp[index] = nums[index];
            return dp[index];
        }

        int max = Math.max(robDfs(nums, dp, index + 1), nums[index] + robDfs(nums, dp, index + 2));
        dp[index] = max;
        return max;
    }


    public int robCycle(int[] nums) {
        int length = nums.length;
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 第一天抢(最后一天不能抢)，变成 [2,length-1] 的子问题
        // 第一天不抢(最后一天可以抢)，变成 [1,length] 的子问题

        return Math.max(nums[0] + rob(Arrays.copyOfRange(nums, 2, length - 1)), rob(Arrays.copyOfRange(nums, 1, length)));
    }



    public static void main(String[] args) {
//        System.out.println(new DpMain().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));

//        System.out.println(new DpMain().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

//        System.out.println(new DpMain().rob(new int[]{2, 1, 1, 2}));
//        System.out.println(new DpMain().rob(new int[]{1, 2, 3, 1}));
//        System.out.println(new DpMain().rob(new int[]{1, 2}));
        System.out.println(new DpMain().robCycle(new int[]{2, 3, 2}));
    }
}
