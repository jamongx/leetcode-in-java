public class ClimbingStairs {



    // Bottom-up Approach
    // using dynamic programming.
    // One can reach the ith step in one of the two ways :
    // Take one step from (i – 1)th step.
    // Take two steps from (i – 2)th step.
    public int sol1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // 중요한 값은 아닌거 같다. 피보나치 수열을 위해서 밑수를 깔아준거 같다
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // without extra memory
    public int sol2(int n) {
        int a = 0, b = 1;
        int res = a + b;

        for (int i = 0; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }

        return res;
    }

    // Brute Force (Recursive) Approach 
    public int sol3(int n) {
        if (n < 2)
            return 1;
        else
            return sol3(n - 1) + sol3(n - 2);
    }

    public static void main(String[] args) {

        ClimbingStairs t = new ClimbingStairs();

        int n = 5;
        System.out.println(t.sol3(n));
    }
}
