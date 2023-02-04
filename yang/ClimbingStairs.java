public class ClimbingStairs {

    /**
     * brute Force (Recursive) Approach
     * @param n
     * @return
     */
    public int sol1(int n) {
        if (n < 2) {
            return 1;
        }
        else {
            return sol1(n - 1) + sol1(n - 2);
        }
    }

    /**
     * dp[i] := # of distinct ways to climb to i-th stair
     * @param n
     * @return
     */
    public int sol2(int n) {
        int[] dp = new int[n + 1];
        // i=2와 연괸이 있는것인가? i=0부터 시작하면0으로 설정해도 되는건가?
        // 미리 계산된 값이다.
        // 중요한 값은 아닌거 같다. 피보나치 수열을 위해서 밑수를 깔아준거 같다
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * without extra memory
     * @param n
     * @return
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

        System.out.println(t.sol1(n));
        System.out.println(t.sol2(n));
        System.out.println(t.sol3(n));
    }
}
