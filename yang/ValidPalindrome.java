public class ValidPalindrome {

    /**
     * https://www.programcreek.com/2013/01/leetcode-valid-palindrome-java/
     * There are several different ways to solve this problem.
     * @param s
     * @return
     */
    public boolean sol1(String s) {
        if (s == null) {
            return false;
        }
        
        s = s.toLowerCase();
        
        int i = 0;
        int j = s.length() - 1;
        
        while (i < j) {
            while (i < j && !((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9'))) {
                i++;
            }
            while (i < j && !((s.charAt(j) >= 'a' && s.charAt(j) <= 'z') || (s.charAt(j) >= '0' && s.charAt(j) <= '9'))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    /**
     * https://walkccc.me/LeetCode/problems/0125/
     * The following is a solution with O(n) time complexity and O(1) space complexity.
     * @param s
     * @return
     */
    public boolean sol2(String s) {
        int left  = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 문자 숫자가 아니면 left++
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 문자 숫자가 아니면 ++
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

}
