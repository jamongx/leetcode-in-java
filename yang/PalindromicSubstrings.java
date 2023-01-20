public class PalindromicSubstrings {
    
    /**
     * https://www.tutorialcup.com/leetcode-solutions/palindromic-substrings-leetcode-solution.htm
     * Time: O(n^2)
     * Space: O(n^2)
     * @param s
     * @return
     */
    public int sol1(String s) {

        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }


        for (int L = 2; L <= n; L++) {
            
            System.out.println("L=" +L);
            for (int i = 0; i + L <= n; i++) {

                int j = i + L - 1;
                // 길이가 2개인 substring
                if (L == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (dp[i][j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * https://walkccc.me/LeetCode/problems/0647/
     * https://youtu.be/y2BD4MJqV20
     * Time: O(n^2)
     * Space: O(1)
     * @param s
     * @return
     */
    public int sol2(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); ++i) {
            ans += extendPalindromes(s, i, i);
            ans += extendPalindromes(s, i, i + 1);
        }

        return ans;
    }

    private int extendPalindromes(String s, int left, int right) {
        int count = 0;

        while ( 0 <= left && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstrings t = new PalindromicSubstrings();

        String s = "pwwkew";
        //String s = "abcabcbb";
        //String s = "bbbbb";
        int k = 1;

        System.out.println(t.sol1(s));

    }
}
