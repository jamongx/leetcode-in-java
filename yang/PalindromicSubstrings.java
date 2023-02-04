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

        // single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 문자열의 길이
        for (int len = 2; len <= n; len++) {
            
            System.out.println("len=" +len);
            // i + len <= n -> len이 2라면 문자열의 길이가 2니까 n 끝까지 갈수 없다
            for (int i = 0; i <= n - len; i++) {

                // i보다 len만큼 길때
                int j = i + len - 1;

                if (len == 2) {
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
        System.out.println("s=" +s);
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // 홀수 길이의 s
            result += extendPalindromes(s, i, i);
            // 짝수 길이의 s
            result += extendPalindromes(s, i, i + 1);
        }

        return result;
    }

    private int extendPalindromes(String s, int left, int right) {
        int count = 0;

        while (0 <= left
               && right < s.length()
               && s.charAt(left) == s.charAt(right)) {
            
            // left == right면 substring은 ""를 return한다.
            System.out.println("s.charAt(" +left +")=" +s.charAt(left) +", s.charAt(" +right +")=" +s.charAt(right));

            count++;
            left--;
            right++;
        }

        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstrings t = new PalindromicSubstrings();

        //String s = "pwwkew";
        String s = "abcabcbb";
        //String s = "bbbbb";

        System.out.println(t.sol1(s));

    }
}
