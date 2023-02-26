import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    // 배열 arr을 만들어서
    // s로 count++
    // t로 count-- 준다.
    // 마지막에 arr에 0이 아닌 값이 있으면 false return
    public boolean sol1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }

        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * map을 사용하고 s의 char를 put한 다음에
     * t의 char를 map에서 remove해서 size가 0보다 크면 false이다.
     * If the inputs contain unicode characters,
     * an array with length of 26 is not enough.
     */
    public boolean sol2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        // <문자, 개수>
        Map<Character, Integer> map = new HashMap<>();

        for (char c1 : s.toCharArray()) {
            if (map.containsKey(c1)) {
                map.put(c1, map.get(c1) + 1);
            } else {
                map.put(c1, 1);
            }
        }

        for (char c2 : t.toCharArray()) {
            if (map.containsKey(c2)) {
                if (map.get(c2) == 1) {
                    map.remove(c2);
                } else {
                    map.put(c2, map.get(c2) - 1);
                }
            } else {
                return false;
            }
        }

        if (map.size() > 0) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        ValidAnagram v = new ValidAnagram();

        String s = "anagram";
        String t = "nagaram";

        System.out.println(v.sol2(s, t));
    }
}
