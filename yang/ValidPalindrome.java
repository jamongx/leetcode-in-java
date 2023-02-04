public class ValidPalindrome {

    /**
     * https://walkccc.me/LeetCode/problems/0125/
     * The following is a solution with O(n) time complexity and O(1) space complexity.
     * letter or digit
     * ((s.charAt(j) >= 'a' && s.charAt(j) <= 'z') || (s.charAt(j) >= '0' && s.charAt(j) <= '9'))
     * @param s
     * @return
     */
    public boolean sol1(String s) {
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

    public static void main(String[] args) {
        ValidPalindrome v = new ValidPalindrome();
        String s = "A man, a plan, a canal: Panama";
        System.out.println(v.sol1(s));
    }

}
