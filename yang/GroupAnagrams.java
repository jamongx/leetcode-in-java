import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    /**
     * https://walkccc.me/LeetCode/problems/0049/
     * Time: O(nklog⁡k), where n = ∣strs∣ and k = ∣strs[i]∣
     * Space: O(nk)
     * 문자열 (str)를 char 배열로 convert 해준다음에 알파벳 순으로 정렬한다.
     * 그리고 map의 key값으로 str을 value list에 add 한다.
     */
    public List<List<String>> sol1(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        GroupAnagrams v = new GroupAnagrams();

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(v.sol1(strs));
    }
}
