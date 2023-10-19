public class PalindromicSubstrings {
    
    /**
     * Dynamic Programming
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int sol1(String s) {

        int n = s.length();

        // dp[i][j], sub-string의 시작 [i], 끝 [j]
        boolean[][] dp = new boolean[n][n];

        // 대각선은 모두 palindrome이다.
        // single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // length of palindrome substring
        for (int l = 2; l <= n; l++) {
            
            // len = 2라면 (n - 2)까지 loop 반복
            for (int i = 0; i <= (n - l); i++) {

                int j = i + l - 1; // end of substring

                if (l == 2) {
                    // true or false
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    // dp[i+1][j-1]는 문자열 앞쥐로 한칸씩 작은 substring
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
     * Naive
     * TC: O(n^2)
     * SC: O(1)
     */
    public int sol2(String s) {
        System.out.println("s=" +s);
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            // 홀수 길이의 sub-string
            // left(i), right(i)가 같은 위치에서 출발한다.
            result += extendPalindromes(s, i, i);
            // 짝수 길이의 sub-string
            // left(i), right(i+1)가 다른 위치에서 출발한다.
            result += extendPalindromes(s, i, i + 1);
        }

        return result;
    }

    private int extendPalindromes(String s, int left, int right) {
        int count = 0;

        // left와 right pointer가 문자열s 안에 포함되어 있다.
        // char(left)와 char(right)가 같을때
        while (0 <= left && right < s.length()
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
