import java.util.Set;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public int sol1(String s) {
        int result = 0;
        // s consists of English letters, digits, symbols and spaces.
        int[] count = new int[128];

        for (int l = 0, r = 0; r < s.length(); r++) {

            ++count[s.charAt(r)];
            
            // 1보다 크다는 것은 중복이 생겼다는 의미이므로
            // loop를 돌면서 중복이 없어질때까지 char할때까지 l을 증가 시킨다.
            while (count[s.charAt(r)] > 1) {
                --count[s.charAt(l)];
                l++;
            }

            // while loop를 빠져나오면 중복이 없는 sliding window의 크기가 나온다.
            // max값과 비교
            result = Math.max(result, r - l + 1);
            
            char cl = s.charAt(l);
            char cr = s.charAt(r);
            System.out.println("left, s.charAt(" +l +")=" +cl +", count[" +cl +"]=" +count[cl]
                              +", right, s.charAt(" +r +")=" +cr +", count[" +cr +"]=" +count[cr]
                              +", ans=" +result);
        }

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
                // i 가 loop를 돌다가 새로 뽑은 char c가 set에 포함되어 있다면
                // 앞선 loop에서 set에 c를 넣었다는 것을 의미한다.
                // j를 loop를 시작한다. set에 저장된 char를 하나씩 비워준다.
                // a == c라면 while loop를 break 한다. remove하고 add한것과 같다.
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

        String s = "abcabcbb";
        //String s = "bbbbb";
        //String s = "pwwkew";

        //System.out.println(t.sol2(s));
        System.out.println(t.sol1(s));

    }
}
