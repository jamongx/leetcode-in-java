public class LongestCommonSubsequence {

    public int sol1(String a, String b) {
        int m = a.length();
        int n = b.length();

        // dp[i][j] := LCS's length of text1[0..i) and text2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        // i는 one, j는 two의 index
        // dp[0][j], dp[i][0] 은 초기값 0으로 set한다.
        // 대신 i+1, j+1로 dp 배열의 마지막까지 계산한다.
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (a.charAt(i) == b.charAt(j)) {
                    // 같다면 대각선으로 +1씩 증가시킨다.
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                else {
                    // common char 개수 값을 안고 간다.
                    // 다르다면, left(j+1) 또는 above(i+1) 중 max 값을 
                    // dp[i + 1][j + 1]에 넣는다.
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
                Utils.print2Darray(dp);
                System.out.println("-------------------");
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence t = new LongestCommonSubsequence();

        String a = "abcde";
        String b = "ace";

        System.out.println(t.sol1(a, b));
    }
}
