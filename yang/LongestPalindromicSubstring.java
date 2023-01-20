public class LongestPalindromicSubstring {

    /**
     * https://walkccc.me/LeetCode/problems/0005/
     * Approach 1: Naive
     * Time: O(n^2)
     * Space: O(n)
     * @param s
     * @return
     */
    public String sol1(String s) {
        if (s.isEmpty()) {
            return "";
        }

        // [start, end] indices of the longest palindrome in s
        int[] indices = { 0, 0 };

        for (int i = 0; i < s.length(); ++i) {
            int[] indices1 = extend(s, i, i);
            if (indices1[1] - indices1[0] > indices[1] - indices[0])
                indices = indices1;
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                int[] indices2 = extend(s, i, i + 1);
                if (indices2[1] - indices2[0] > indices[1] - indices[0])
                    indices = indices2;
            }
        }

        return s.substring(indices[0], indices[1] + 1);
    }

    // Returns [start, end] indices of the longest palindrome extended from s[i..j]
    private int[] extend(final String s, int i, int j) {

        for (; i >= 0 && j < s.length(); --i, ++j) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
        }
        return new int[] { i + 1, j - 1 };
    }


    /**
     * https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * Approach 1: Naive
     * Time: O(n^2)
     * Space: O(n)
     * @param s
     * @return
     */
    public String sol11(String s) {
        if (s.isEmpty()) {
            return null;
        }

        if (s.length() == 1) {
            return s;
        }

        String longest = s.substring(0, 1);

        for (int i = 0; i < s.length(); i++) {

            // get longest palindrome with center of i
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
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
     * https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
     * Approach 2: Dynamic Programming
     * Time: O(n^2)
     * Space: O(n^2)
     * @param s
     * @return
     */

    public String sol2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];

        String longest = null;
        for (int l = 0; l < len; l++) {

            for (int i = 0; i < (len-l); i++) {

                int j = i + l;

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
     * https://walkccc.me/LeetCode/problems/0005/
     * Approach 3: Manacher
     * Time: O(n)
     * Space: O(n)
     * @param s
     * @return
     */
    public String sol3(String s) {
        final String t = join('@' + s + '$', '#');
        final int n = t.length();
        // t[i - maxExtends[i]..i) ==
        // t[i + 1..i + maxExtends[i]]
        int[] maxExtends = new int[n];
        int center = 0;

        for (int i = 1; i < n - 1; ++i) {
            final int rightBoundary = center + maxExtends[center];
            final int mirrorIndex = center - (i - center);
            maxExtends[i] = rightBoundary > i && Math.min(rightBoundary - i, maxExtends[mirrorIndex]) > 0 ? 1 : 0;

            // Attempt to expand palindrome centered at i
            while (t.charAt(i + 1 + maxExtends[i]) == t.charAt(i - 1 - maxExtends[i]))
                ++maxExtends[i];

            // If palindrome centered at i expand past rightBoundary,
            // adjust center based on expanded palindrome.
            if (i + maxExtends[i] > rightBoundary)
                center = i;
        }

        // Find the maxExtend and bestCenter
        int maxExtend = 0;
        int bestCenter = -1;

        for (int i = 0; i < n; ++i)
            if (maxExtends[i] > maxExtend) {
                maxExtend = maxExtends[i];
                bestCenter = i;
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

}
