
public class UniquePaths {

    // DFS
    public int sol1(int m, int n) {
        return dfs(0, 0, m, n);
    }

    public int dfs(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i < m - 1 && j < n - 1) {
            return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
        }

        if (i < m - 1) {
            return dfs(i + 1, j, m, n);
        }

        if (j < n - 1) {
            return dfs(i, j + 1, m, n);
        }

        return 0;
    }
    
    // Dynamic Programming
    public int sol2(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;

        int[][] dp = new int[m][n];

        // left column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
            System.out.println("left ------------------");
            Utils.print2Darray(dp);
        }

        // top row
        System.out.println("right ------------------");
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
            System.out.println("right ------------------");
            Utils.print2Darray(dp);
        }

        // fill up the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                System.out.println("fill ------------------");
                Utils.print2Darray(dp);
            }
        }

        return dp[m - 1][n - 1];
    }

    // Dynamic Programming with Memoization
    public int sol3(int m, int n) {
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

    public static void main(String[] args) {
        UniquePaths t = new UniquePaths();

        int m = 3;
        int n = 2;        

        System.out.println(t.sol2(m, n));
    }    
}
