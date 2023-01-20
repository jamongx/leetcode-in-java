import java.util.Set;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public int sol1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        boolean[] flag = new boolean[256];
        int result = 0;
        int start = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char current = arr[i];
            if (flag[current]) {
                result = Math.max(result, i - start);
                // the loop update the new start point
                // and reset flag array
                // for example, abccab, when it comes to 2nd c,
                // it update start from 0 to 3, reset flag for a,b
                for (int k = start; k < i; k++) {
                    if (arr[k] == current) {
                        start = k + 1;
                        break;
                    }
                    flag[arr[k]] = false;
                }
            } else {
                flag[current] = true;
            }
        }
        result = Math.max(arr.length - start, result);
        return result;
    }

    /*
     * https://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
     * Using a HashSet can simplify the code a lot.
     * sliding window -> hashset을 사용
     */
    public int sol2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        //int i = 0;
        
        int result = 1;
        for (int i = 0, j = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (!set.contains(c)) {
                set.add(c);
                System.out.println("added, " +set);
                result = Math.max(result, set.size());
            }
            else {
                // 두개의 pointer -> i = 0, j = 0 에서 시작
                // i pointer가 loop를 돌다가 새로 뽑은 char c가 set에 포함되어 있다면
                // 앞선 loop에서 set에 c를 넣었다는 것을 의미한다.
                // 두번째 pointer j를 loop를 시작한다.
                // set에 저장된 char를 하나씩 비워준다.
                while (i > j) {
                    char a = s.charAt(j++);
                    if (a != c) {
                        set.remove(a);
                        System.out.println("removed, " +set);
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters t = new LongestSubstringWithoutRepeatingCharacters();

        //String s = "abcabcbb";
        //String s = "bbbbb";
        String s = "pwwkew";

        System.out.println(t.sol2(s));

    }
}
