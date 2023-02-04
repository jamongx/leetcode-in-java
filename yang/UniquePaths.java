import java.util.Arrays;

public class UniquePaths {

    /**
     * Approach 1: 2D DP
     * TC: O(mn)
     * SC: O(mn)
     * @param m
     * @param n
     * @return
     */
    public int sol1(int m, int n) {
        // dp[i][j] := unique paths from (0, 0) to (i, j)
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 1));

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * Approach 2: 1D DP 
     * TC: O(mn)
     * SC: O(n)
     * @param m
     * @param n
     * @return
     */
    public int sol2(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        UniquePaths t = new UniquePaths();

        int m = 3;
        int n = 2;        

        System.out.println(t.sol1(m, n));
        System.out.println(t.sol2(m, n));
    }    
}
