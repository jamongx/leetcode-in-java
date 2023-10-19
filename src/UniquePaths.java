import java.util.Arrays;

public class UniquePaths {

    /**
     * Approach 1: 2D DP
     * Bottom-up Approach (Tabulation)
     * TC: O(mn)
     * SC: O(mn)
     */
    public int sol1(int m, int n) {
        int[][] dp = new int[m][n];

        // 첫 번째 행과 첫 번째 열을 1로 채움
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 나머지 셀을 채움
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Approach 1: 2D DP
     * Top-down Approach (Memoization)
     * TC: O(mn)
     * SC: O(mn)
     */
    public int sol2(int m, int n) {
        int[][] mem = new int[m][n];

        // init with -1 value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mem[i][j] = -1;
            }
        }

        return helper(mem, m - 1, n - 1);
    }

    private int helper(int[][] mem, int m, int n) {
        // edge has only one path
        if (m == 0 || n == 0) {
            mem[m][n] = 1;
            return 1;
        }
        if (mem[m][n] != -1) {
            return mem[m][n];
        }
        mem[m][n] = helper(mem, m, n - 1) + helper(mem, m - 1, n);
        return mem[m][n];
    }

    /**
     * Approach 2: 1D DP 
     * TC: O(mn)
     * SC: O(n)
     */
    public int sol3(int m, int n) {
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
        System.out.println(t.sol3(m, n));
    }    
}
