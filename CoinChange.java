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

    /**
     * 예를 들어, coins = [1, 2, 5]이고 amount = 11인 경우를 살펴봅시다.
     * dp[11] (11을 만드는 데 필요한 동전의 최소 개수)를 초기에 amount + 1로 설정합니다.
     * 각 동전 coin에 대해, 11 - coin의 값 (즉, 10, 9, 6)이 dp 배열에 이미 저장되어 있다면,
     * 그 값을 이용해서 dp[11]을 업데이트합니다.
     * dp[11] = min(dp[11], dp[10] + 1)
     * dp[11] = min(dp[11], dp[ 9] + 1)
     * dp[11] = min(dp[11], dp[ 6] + 1)
     * 이를 통해 dp[11] 값을 최소로 만듭니다.
     * @param coins
     * @param amount
     * @return
     */
    public int sol2(int[] coins, int amount) {

        // amount는 사용할 수 있는 coin의 개수
        // dp는 coin의 개수가 저장 (0부터 amount+1)까지
        int[] dp = new int[amount + 1];

        // 처음에는 모든 값을 amount + 1로 설정하여 "아직 계산되지 않음"을 나타냅니다.
        // amount + 1은 불가능한 큰 값으로 설정
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


   public static void main(String[] args) {

        CoinChange t = new CoinChange();

        int[] coins = {1, 2, 5, 9, 8};
        //int[] coins = {1, 2, 5};
        int amount = 17;
        System.out.println(t.sol2(coins, amount));
    }
}
