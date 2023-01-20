public class LongestCommonSubsequence {

    public int sol1(String a, String b) {
        int m = a.length();
        int n = b.length();
        // 바로 앞에 계산된 값을 더해야 하므로 앞에 자리하나가 더 필요하다.
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else 
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        LongestCommonSubsequence t = new LongestCommonSubsequence();

        String a = "aslkdjfl";
        String b = "askdljf";

        System.out.println(t.sol1(a, b));
    }
}
