public class ValidPalindrome {

    /**
     * https://walkccc.me/LeetCode/problems/0125/
     * TC: O(n)
     * SC: O(1)
     * Check letter or digit
     * ((s.charAt(j) >= 'a' && s.charAt(j) <= 'z')
     *  || (s.charAt(j) >= '0' && s.charAt(j) <= '9'))
     * 문자열(s)의 시작과 끝에서 2개의 pointer로 각각 loop를 돌면서 문자들을 비교한다.
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
            if (Character.toLowerCase(s.charAt(left))
             != Character.toLowerCase(s.charAt(right))) {
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
