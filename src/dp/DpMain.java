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


    public int maxSubArrayDp(int[] nums) {
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


    public int lengthOfLIS(int[] nums) {
        int[] mem = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            lengthOfLISDfs(nums, mem, i);
            max = Math.max(max, mem[i]);

        }
        return max;
    }

    // [10,9,2,5,3,7,101,18]
    public int lengthOfLISDfs(int[] nums, int[] mem, int start) {
        if (start >= nums.length - 1) {
            mem[start] = 1;
            return 1;
        }

        if (mem[start] != 0) {
            return mem[start];
        }

        int max = 1;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] > nums[start]) {
                int len = lengthOfLISDfs(nums, mem, i) + 1;
                max = Math.max(max, len);
            }

        }

        mem[start] = max;
        return max;
    }

    public int maxSubArrayDp2(int[] nums) {
        int max = nums[0];
        int preSubMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preSubMax < 0) {
                preSubMax = nums[i];
            } else {
                preSubMax = preSubMax + nums[i];
            }
            max = Math.max(preSubMax, max);
        }
        return max;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        // 2
        int a = 0;
        // 3
        int b = 0;
        // 5
        int c = 0;

        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[a] * 2, Math.min(dp[b] * 3, dp[c] * 5));
            dp[i] = min;
            if (min == dp[a] * 2) {
                a++;
            }
            if (min == dp[b] * 3) {
                b++;
            }
            if (min == dp[c] * 5) {
                c++;
            }
        }

        return dp[n - 1];
    }





    public static void main(String[] args) {
//        System.out.println(new DpMain().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));

//        System.out.println(new DpMain().maxSubArrayDp(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        System.out.println(new DpMain().maxSubArrayDp2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

//        System.out.println(new DpMain().rob(new int[]{2, 1, 1, 2}));
//        System.out.println(new DpMain().rob(new int[]{1, 2, 3, 1}));
//        System.out.println(new DpMain().rob(new int[]{1, 2}));
//        System.out.println(new DpMain().robCycle(new int[]{2, 3, 2}));

//        System.out.println(new DpMain().lengthOfLIS(new int[]{0}));
//        System.out.println(new DpMain().lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(new DpMain().nthUglyNumber(11));
    }
}
