public class BestTimetoBuyandSellStock {

    // Brute force
    public int sol1(int[] prices) {

        int len = prices.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    /**
     * Dynamic Programming (Kadane's Algorithm)
     * TC: O(N)
     * SC: O(1)
     */
    public int sol2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        // min sell price
        int minPrice = prices[0];

        // 먼저 매수하고 다음에 매도하는 순서가 보장된다.
        for (int i = 1; i < prices.length; i++) {
            // 이익 = 현재 가격 (판매) - 가장 싼 구매 가격
            int profit = prices[i] - minPrice;

            // 최대 이익
            maxProfit = Math.max(maxProfit, profit);

            // 현재 가격 (구매), 가장 저렴했던 가격
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStock t = new BestTimetoBuyandSellStock();

        int[] price = { 7, 1, 5, 3, 6, 4 };

        System.out.println(t.sol2(price));
    }

}
