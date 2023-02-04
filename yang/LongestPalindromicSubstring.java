public class LongestPalindromicSubstring {

    /**
     * Dynamic Programming
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public String sol1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];

        String longest = null;
        for (int l = 0; l < len; l++) { // length of palindrome substring
            for (int i = 0; i < (len - l); i++) {

                int j = i + l; // end of substring

                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {

                    dp[i][j] = true;

                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        longest = s.substring(i, j + 1);
                    }
                }
            }
        }
        return longest;
    }


    /**
     * Naive
     * TC: O(n^2)
     * SC: O(1)
     */
    public String sol2(String s) {
        if (s.isEmpty()) {
            return null;
        }

        if (s.length() == 1) {
            return s;
        }

        String longest = s.substring(0, 1);

        for (int i = 0; i < s.length(); i++) {

            // odd length, get longest palindrome with center of i
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // even length, get longest palindrome with center of i, i+1
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }

        return longest;
    }

    /**
     * Given a center, either one letter or two letter,
     * Find longest palindrome
     */
    public String helper(String s, int begin, int end) {
        // begin이 0과 같거나 크고
        // end가 s의 길이보다 작거나 같고
        // s(begin)과 s(end)와 같다면 -> loop를 돈다.
        while (0 <= begin && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }


    /**
     * Manacher
     * TC: O(n)
     * SC: O(n)
     */
    public String sol3(String s) {
        String t = join('@' + s + '$', '#');
        int n = t.length();
        // t[i - maxExtends[i]..i) ==
        // t[i + 1..i + maxExtends[i]]
        int[] maxExtends = new int[n];
        int center = 0;

        for (int i = 1; i < n - 1; i++) {
            int rightBoundary = center + maxExtends[center];
            int mirrorIndex = center - (i - center);
            maxExtends[i] = rightBoundary > i && Math.min(rightBoundary - i, maxExtends[mirrorIndex]) > 0 ? 1 : 0;

            // Attempt to expand palindrome centered at i
            while (t.charAt(i + 1 + maxExtends[i]) == t.charAt(i - 1 - maxExtends[i])) {
                ++maxExtends[i];
            }

            // If palindrome centered at i expand past rightBoundary,
            // adjust center based on expanded palindrome.
            if (i + maxExtends[i] > rightBoundary) {
                center = i;
            }
        }

        // Find the maxExtend and bestCenter
        int maxExtend = 0;
        int bestCenter = -1;

        for (int i = 0; i < n; ++i) {
            if (maxExtends[i] > maxExtend) {
                maxExtend = maxExtends[i];
                bestCenter = i;
            }
        }

        return s.substring((bestCenter - maxExtend) / 2, (bestCenter + maxExtend) / 2);
    }

    private String join(final String s, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            if (i != s.length() - 1)
                sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring t = new LongestPalindromicSubstring();
        String s = "babad";

        System.out.println(t.sol3(s));
        
    }

}
