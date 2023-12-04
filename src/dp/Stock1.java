package dp;

/**
 * @author : mocun
 * @since : 2023/12/4
 */
public class Stock1 {

    public int maxProfit(int[] prices) {
        // 记录最小的价格
        int minPrice = prices[0];
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            // 卖的时候一定是找之前最小的价格卖
            max = Math.max(max, prices[i] - minPrice);
            // 更新最小的价格
            minPrice = Math.min(minPrice, prices[i]);
        }

        return max;
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

            // 持股，要么昨天也持股，要么昨天不持股，今天买了（因为只允许买一次，所以利润就是直接 0-股价）
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        System.out.println(new Stock1().maxProfitDp(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
