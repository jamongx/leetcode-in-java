import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    /**
     * TC: O(n)
     * SC: O(128)=O(1)
     * @param s
     * @param k
     * @return
     */
    public int sol1(String s, int k) {
        // max sliding window size
        int result = 0;

        // sliding window 안에서 제일 빈도가 높은 char의 개수
        int maxRepeat = 0;

        // 'A'를 빼주면 배열의 크기를 26으로 줄일수 있다.
        int[] count = new int[128];

        for (int l = 0, r = 0; r < s.length(); r++) {

            // sliding window에 포함된 그 문자의 count를 +1
            maxRepeat = Math.max(maxRepeat, ++count[s.charAt(r)]);

            // sliding window 안에 max_character가 있다.
            // sliding window size - maxChount => replace count
            while (Utils.windowSize(l, r) - maxRepeat > k) {
                
                // replace count > k 라면 sliding window를 -1 shrink
                --count[s.charAt(l)];
                l++;
            }

            result = Math.max(result, Utils.windowSize(l, r));
        }

        return result;
    }


    public int sol2(String s, int k) {

        int result = 0;
        int maxRepeat = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int l = 0, r = 0; r < s.length(); r++) {

            char chR = s.charAt(r);

            map.put(chR, map.getOrDefault(chR, 0) + 1);

            // see the explanation in the 'Solution' section above.
            maxRepeat = Math.max(maxRepeat, map.get(chR));

            // current window size is from windowStart to windowEnd, overall we have a letter which is repeating 'maxRepeatLetterCount' times,
            // this means we can have a window which has one letter repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we are not allowed to replace more than 'k' letters
            while (Utils.windowSize(l, r) - maxRepeat > k) {
                char chL = s.charAt(l);
                map.put(chL, map.get(chL) - 1);
                l++;
            }

            result = Math.max(result, Utils.windowSize(l, r));
        }
        return result;
    }


    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement t = new LongestRepeatingCharacterReplacement();

        String s = "abcabcbb";
        //String s = "bbbbb";
        int k = 1;
        //String s = "pwwkew";

        System.out.println(t.sol1(s, k));
        System.out.println(t.sol2(s, k));

    }
}
