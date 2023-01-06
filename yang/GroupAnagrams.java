import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {


    /**
     * https://www.programcreek.com/2014/04/leetcode-anagrams-java/
     * If the average length of verbs is m and array length is n, then the time is O(n*m). 
     * @param strs
     * @return
     */
    public List<List<String>> sol1(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }

            // ns는 char[26]을 문자열로 바꾼것인데 print는 안된다.
            String ns = new String(arr);
            if (map.containsKey(ns)) {
                map.get(ns).add(str);
            } else {
                List<String> al = new ArrayList<>();
                al.add(str);
                map.put(ns, al);
            }
        }
        result.addAll(map.values());
        return result;
    }

    /**
     * https://walkccc.me/LeetCode/problems/0049/
     * Time: O(nklog⁡k), where n = ∣strs∣ and k = ∣strs[i]∣
     * Space: O(nk)
     * @param strs
     * @return
     */
    public List<List<String>> sol2(String[] strs) {

        Map<String, List<String>> keyToAnagrams = new HashMap<>();

        for (final String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            keyToAnagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(keyToAnagrams.values());
    }


    public static void main(String[] args) {
        GroupAnagrams v = new GroupAnagrams();

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(v.sol1(strs));
    }
}
