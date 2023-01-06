import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    /**
     * O(n) time, O(k) space
     * @param s
     * @param k
     * @return
     */
    public int sol1(String s, int k) {

        int maxLen = 0;

        // 입력 문자열중에서 제일 많은 문자의 개수
        // the number of a character, which has the most frequency
        int maxCharacter = 0;
        
        // 'A'를 빼주면 배열의 크기를 26으로 줄일수 있다.
        int[] count = new int[128];
        
        for (int window_start = 0, window_end = 0; window_end < s.length(); window_end++) {
            
            // window에 포함되므로 count를 ++ 해줌 
            maxCharacter = Math.max(maxCharacter, ++count[s.charAt(window_end)]);
            
            int window_size = window_end - window_start + 1;

            // window_size가 있고 그 window 안에 max_character가 있는데
            // 그 차이가 k값 보다 크다면 => invalid window
            int replaceCount = window_size - maxCharacter; // replaceCount -> the number of character replacement
            if (replaceCount > k) {
                // invalid window
                // window_start가 하나 shirnk 되므르
                // window에서 빠진다. 그래서 count를 -- 해줌 
                --count[s.charAt(window_start)];
                window_start++;
            }

            maxLen = Math.max(maxLen, window_end - window_start + 1);
        }

        return maxLen;
    }
    

    public int sol3(String s, int k) {
        char[] arr = s.toCharArray();

        // Define table
        Map<Character, Integer> hm = new HashMap<>();

        int left = 0;
        int right = 0;
        int maxLen = 0;
        int mostFreq = 0;

        // find longest repeating character replacement
        while (right < arr.length) {
            // Expand the window
            hm.put(arr[right], hm.getOrDefault(arr[right], 0) + 1);
            mostFreq = Math.max(mostFreq, hm.get(arr[right]));

            // Shrink the window if we need to replace more than k char
            if ((right - left + 1) - mostFreq > k) {

                hm.put(arr[left], hm.get(arr[left]) - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }


    public int sol2(String s, int k) {

        int windowStart = 0;
        int maxRepeat = 0;
        int result = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char chWE = s.charAt(windowEnd);

            map.put(chWE, map.getOrDefault(chWE, 0) + 1);

            // we don't need to place the maxRepeatLetterCount under the below 'if',
            // see the explanation in the 'Solution' section above.
            maxRepeat = Math.max(maxRepeat, map.get(chWE));

            // current window size is from windowStart to windowEnd, overall we have a letter which is repeating 'maxRepeatLetterCount' times,
            // this means we can have a window which has one letter repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we are not allowed to replace more than 'k' letters
            if (windowEnd - windowStart + 1 - maxRepeat > k) {
                char chWS = s.charAt(windowStart);
                map.put(chWS, map.get(chWS) - 1);
                windowStart++;
            }

            result = Math.max(result, windowEnd - windowStart + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement t = new LongestRepeatingCharacterReplacement();

        //String s = "abcabcbb";
        //String s = "bbbbb";
        int k = 1;
        String s = "pwwkew";

        System.out.println(t.sol1(s, k));

    }
}
