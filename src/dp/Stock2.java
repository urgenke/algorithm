package dp;

/**
 * @author : mocun
 * @since : 2023/12/4
 */
public class Stock2 {

    public int maxProfit(int[] prices) {
        // 记录最小的价格
        int minPrice = prices[0];
        int profit = 0;
        // 面条拉直了吃
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice) {
                profit += prices[i] - minPrice;
                minPrice = prices[i];
            } else {
                minPrice = Math.min(minPrice, prices[i]);
            }
        }

        return profit;
    }

    public int maxProfitDp(int[] prices) {
        /*
        dp[i][j] = x
        i 代表到目前 i 天为止
        j 代表持股状态，0不持股，1持股
        x 代表获取的利润，初始值为 0，最后一定是不持股利润最大
         */

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 不持股，要么昨天也不持股，要么昨天持股但是今天卖了，找一个最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 持股，要么昨天也持股，要么昨天不持股，今天买了
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
