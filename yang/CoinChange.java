import java.util.Arrays;

public class CoinChange {

    /**
     * The naive approach is to check for every combination of coins for the given sum
     */
    public int sol1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        int fewest = Integer.MAX_VALUE;
        for (int coin : coins) {

            int result = sol1(coins, amount - coin);
            if (result != Integer.MAX_VALUE) {
                fewest = Math.min(fewest, result + 1);
            }
        }

        return fewest;
    }

    // permutation
    public int sol4(int[] coins, int amount) {

        // dp[i] := fewest # of coins to make up i
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, amount + 1);

        for (int i = 1; i <= amount; i++)
            for (int coin : coins)
                if (coin <= i)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

 

    public int sol2(int[] coins, int amount) {

        // amount는 coin의 개수가 저장
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;

            // 금액(i)를 만드는 coin의 개수 계산하는 부분
            for (int coin : coins) {
                int j = i - coin;
                if (j >= 0) {
                    // 금액 j를 만드는 coin의 개수에 1를 더한 개수와
                    // 금액 i를 만드는 coin의 개수중에 min 값을 선택
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[amount];
    }

    /**
     * 코인의 개수를 저장
     * dp[i] := fewest # of coins to make up i
     * 
     */
    public int sol3(int[] coins, int amount) {

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, 1, dp.length, amount + 1);

        for (int coin : coins) {


            for (int i = coin; i <= amount; i++) {
                
                System.out.print("coin=" +coin +", i=" +i +", i-coin=" +(i-coin) +", dp[i=" +i +"]=" +dp[i] +", dp[i-coin=" +(i-coin) +"]=" +dp[i-coin]);
                dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 이게 뭐야?
                System.out.println(", new dp[i=" +i +"]=" +dp[i]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
    

   public static void main(String[] args) {

        CoinChange t = new CoinChange();

        int[] coins = {1, 2, 5, 9, 8};
        //int[] coins = {1, 2, 5};
        int amount = 17;
        System.out.println(t.sol3(coins, amount));
    }
}
