public class LongestCommonSubsequence {

    public int sol1(String one, String two) {
        int m = one.length();
        int n = two.length();

        // 바로 앞에 계산된 값을 더해야 하므로 앞에 자리하나가 더 필요하다.
        // dp[i][j] := LCS's length of text1[0..i) and text2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        // indexing에 주의해야 된다.
        // dp[0][j], dp[i][0] 은 초기값 0으로 내버려 둔다.
        // 대신 i+1, j+1로 dp 배열의 마지막까지 계산한다.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(one.charAt(i) == two.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        Utils.print2Darray(dp);
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence t = new LongestCommonSubsequence();

        String a = "abcde";
        String b = "abc";

        System.out.println(t.sol1(a, b));
    }
}
