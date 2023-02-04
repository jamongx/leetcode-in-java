import java.util.Map;
import java.util.HashMap;

public class MinimumWindowSubstring {

    // s = "ADOBECODEBANC"
    // t = "ABC"
    public String sol1(String s, String t) {

        int[] count = new int[128];
        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int bestLeft = -1; // left location

        // 1자리일때는 +1이 필요하다
        int minLength = s.length() + 1; // src len + 1

        int required = t.length(); // target len

        for (int l = 0, r = 0; r < s.length(); r++) {

            if (--count[s.charAt(r)] >= 0) {
                required--;
                System.out.println("required--=" +required);
            }

            while (required == 0) {

                if (minLength > Utils.windowSize(l, r)) {
                    bestLeft = l;
                    minLength = Utils.windowSize(l, r);
                    System.out.println("bestLeft=" +bestLeft +", minLength=" +minLength);
                }

                if (++count[s.charAt(l++)] > 0) {
                    required++;
                    System.out.println("required++=" +required);
                }
            }
        }
        
        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring min = new MinimumWindowSubstring();

        //String s = "ADOBECODEBANC";
        //String t = "ABC";
        String s = "a";
        String t = "aa";

        System.out.println(min.sol1(s, t));
        //System.out.println(min.sol2(s, t));
    }


    // s = "ADOBECODEBANC", t = "ABC"
    public String sol2(String s, String t) {

        // target dictionary
        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        
        int total     = 0;
        int minLen    = Integer.MAX_VALUE;
        String result = "";

        HashMap<Character, Integer> map = new HashMap<>();

        for (int l = 0, r = 0; r < s.length(); r++) {

            char curr = s.charAt(r);
            if (!target.containsKey(curr)) {
                continue; // s의 문자 하나가 goal에 없으면 skip
            }

            // if c is a target character in the goal and count is < goal, increase the total.
            int count = map.getOrDefault(curr, 0);
            if (count < target.get(curr)) {
                total++;
            }
            map.put(curr, count + 1);

            // when total reaches the goal, trim from left until no more chars can be trimmed.
            if (total == t.length()) {

                while (!target.containsKey(s.charAt(l)) || map.get(s.charAt(l)) > target.get(s.charAt(l))) {
                    char pc = s.charAt(l);
                    if (target.containsKey(pc) && map.get(pc) > target.get(pc)) {
                        map.put(pc, map.get(pc) - 1);
                    }
                    l++;
                }

                if (minLen > r - l + 1) {
                    minLen = r - l + 1;
                    result = s.substring(l, r + 1);
                }
            }
        }
        return result;
    }


}
