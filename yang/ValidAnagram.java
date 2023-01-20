import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

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
     * If the inputs contain unicode characters, an array with length of 26 is not enough.
     * @param s
     * @param t
     * @return
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
