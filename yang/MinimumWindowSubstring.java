import java.util.HashMap;

public class MinimumWindowSubstring {
   
    public String sol1(String s, String t) {

        // target dictionary
        HashMap<Character, Integer> goal = new HashMap<>();
        for (int k = 0; k < t.length(); k++) {
            // 여러개 있을수 있으므로 getOrDefault()를 사용한다.
            goal.put(t.charAt(k), goal.getOrDefault(t.charAt(k), 0) + 1);
        }
        
        int total     = 0;
        int minLen    = Integer.MAX_VALUE;
        String result = "";

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0, j = 0; j < s.length(); j++) {

            char c = s.charAt(j);
            if (!goal.containsKey(c)) {
                // s의 문자 하나가 goal에 없으면 skip
                continue;
            }

            // if c is a target character in the goal and count is < goal, increase the total.
            int count = map.getOrDefault(c, 0);
            if (count < goal.get(c)) {
                total++;
            }
            map.put(c, count + 1);

            // when total reaches the goal, trim from left until no more chars can be trimmed.
            if (total == t.length()) {

                while (!goal.containsKey(s.charAt(i)) || map.get(s.charAt(i)) > goal.get(s.charAt(i))) {
                    char pc = s.charAt(i);
                    if (goal.containsKey(pc) && map.get(pc) > goal.get(pc)) {
                        map.put(pc, map.get(pc) - 1);
                    }
                    i++;
                }

                if (minLen > j - i + 1) {
                    minLen = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    //////////////////////////////////////////////////////////////////////////
    public String sol2(String s, String t) {

        int[] count = new int[128];
        for (final char c : t.toCharArray()) {
            ++count[c];
        }

        int required = t.length();
        int bestLeft = -1;
        int minLength = s.length() + 1;

        for (int l = 0, r = 0; r < s.length(); ++r) {

            if (--count[s.charAt(r)] >= 0) {
                --required;
            }

            while (required == 0) {

                if (r - l + 1 < minLength) {
                    bestLeft = l;
                    minLength = r - l + 1;
                }

                if (++count[s.charAt(l++)] > 0) {

                }
                    ++required;
            }
        }

        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring t = new MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String o = "ABC";
        //Output: "BANC"

        System.out.println(t.sol1(s, o));
    }

}
