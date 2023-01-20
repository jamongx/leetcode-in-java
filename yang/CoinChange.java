public class CoinChange {

    // The naive approach is to check for every combination of coins for the given sum
    public int sol1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        int fewest_num = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = sol1(coins, amount - coin);
            if (result != Integer.MAX_VALUE) {
                fewest_num = Integer.min(fewest_num, result + 1);
            }
        }
        return fewest_num;
    }


    public int sol2(int[] coins, int amount) {

        // amount는 coin의 개수가 저장
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;

            // 금액(i)를 만드는 coin의 개수 계산하는 부분
            for (int c : coins) {
                int j = i - c;
                if (j >= 0) {
                    // 금액 j를 만드는 coin의 개수에 1를 더한 개수와
                    // 금액 i를 만드는 coin의 개수중에 min 값을 선택
                    dp[i] = Integer.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[amount];
    }
    

    public static void main(String[] args) {

        CoinChange t = new CoinChange();

        int[] coins = {1, 2, 5, 9, 8};
        //int[] coins = {1, 2, 5};
        int amount = 17;
        System.out.println(t.sol2(coins, amount));
    }
}
