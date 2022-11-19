public class BestTimetoBuyandSellStock {

    public int sol1(int[] prices) {

        // Brute force
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

        // return the maximum profit you can achieve
        // if you can't achieve any profit, return 0.
        int maxProfit = 0;

        // minmum price
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 현재 팔수 있는 값 - 가장 싸게 산 가격
            int profit = prices[i] - minPrice;

            // 최대 profit 저장
            maxProfit = Math.max(maxProfit, profit);

            // 현재 인덱스까지의 싸게 살 수 있는 값
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] price = {7,1,5,3,6,4};

        BestTimetoBuyandSellStock t = new BestTimetoBuyandSellStock();
        System.out.println(t.sol2(price));
    }

}
