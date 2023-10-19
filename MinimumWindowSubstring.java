import java.util.Map;
import java.util.HashMap;

public class MinimumWindowSubstring {

    // s = "ADOBECODEBANC"
    // t = "ABC"
    public String sol1(String s, String t) {
        // window가 유효하다
        // 1. required가 0이다.
        // 2. count 배열의 모든 값들이 0이다.

        // 먼저 t를 기준으로 count++
        // 최적화를 한다면 줄일수 있다.
        int[] count = new int[128];
        for (char c : t.toCharArray()) {
            count[c]++; // t char의 개수를 계산한다
        }

        int bestLeft  = -1;             // left location
        int minLength = s.length() + 1; // s->len 1일때는 +1이 필요하다
        int required  = t.length();     // t->len

        // s를 기준으로 loop -> 오른쪽 pointer
        // count의 배열은 t의 char 위치의 값이 > 0 상태이다.

        for (int l = 0, r = 0; r < s.length(); r++) {

            // right pointer move 
            // t의 char과 일치한 s의 char count값이 0크거나 같으면
            // -> 포함된다는 뜻이다.
            // right pointer의 이동은 count를 감소시킨다.
            // t와 겹치지 않으면 0보다 작을것이다.
            // 그래서 0보다 크거나 같으면 겹치는것이니까 required를 --
            if (--count[s.charAt(r)] >= 0) {
                required--;
                Utils.printArray(count);
            }

            // 내부 loop windows가 유효할때, left를 증가시켜서
            // 가장 작은 window를 찾기 위함
            // When we found a valid window, move left to find smaller window.
            while (required == 0) {

                // If the window's substring is shorter, update the res
                // 더 작은 window를 찾았다.
                if (minLength > Utils.windowSize(l, r)) {
                    bestLeft = l;
                    minLength = Utils.windowSize(l, r);
                }

                // left pointer move 
                // left pointer의 이동은 count를 증가시킨다.
                // t의 char과 일치한 s의 char count값이 0크거나 같으면
                // 겹치지 않으면 right가 지나가면 0보다 작은 값이다.
                // left가 이 내부 loop에서 지나가면 
                if (++count[s.charAt(l++)] > 0) {
                    required++;
                    Utils.printArray(count);
                }
            }
        }
        
        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
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

    public static void main(String[] args) {
        MinimumWindowSubstring min = new MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";
        //String s = "a";
        //String t = "aa";

        System.out.println(min.sol1(s, t));
        //System.out.println(min.sol2(s, t));
    }

}
