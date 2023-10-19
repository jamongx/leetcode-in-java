public class ClimbingStairs {

    /**
     * Memoization (Recursive) Approach
     * TC: O(n)
     * SC: O(n)
     */
    private static int[] memo;

    public static int sol1(int n) {
        if (memo == null) {
            memo = new int[n + 1];
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n] = sol1(n - 1) + sol1(n - 2);
        return memo[n];
    }

    /**
     * dp[i] := # of distinct ways to climb to i-th stair
     * 2D DP
     * TC: O(n)
     * SC: O(n)
     */
    public int sol2(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * without extra memory
     * 1D DP
     * TC: O(n)
     * SC: O(1)
     */
    public int sol3(int n) {
        int dp0 = 1; // int dp0 = 0; -> 0부터 시작하려면 int i의 값을 바꿔야 한다.
        int dp1 = 1;

        // for (int i = 0; i < n; i++) {
        for (int i = 2; i <= n; i++) {
            int dp = dp0 + dp1;
            dp0 = dp1;
            dp1 = dp;
        }

        return dp1;
    }

    public static void main(String[] args) {
        ClimbingStairs t = new ClimbingStairs();

        int n = 5;

        System.out.println(ClimbingStairs.sol1(n));
        System.out.println(t.sol2(n));
        System.out.println(t.sol3(n));
    }
}
