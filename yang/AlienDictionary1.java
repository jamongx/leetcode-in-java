import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://www.tutorialcup.com/leetcode-solutions/alien-dictionary-leetcode-solution.htm
 */
public class AlienDictionary1 {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> dict = new HashMap<>();
        Map<Character, Boolean> visited = new HashMap<>();
        
    // add character to visited map, mark all of them as unvisited
        for (String str: words) {
            for (char ch: str.toCharArray()) {
                visited.put(ch, false);
            }
        }
    
    // 
        for (int i = 0; i < words.length-1; i++) {
            if(!fillDict(dict, words[i], words[i+1])) {
                // invalid input, ex:- ab,abc is not a valid dictionary word
                return "";
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char ch: visited.keySet()) {
            if (!visited.get(ch)) {
                // do the topological sorting from each unvisited dictionary character
                if (!topological(dict, ch, visited, new HashSet<>(), builder)) {
                    // loop detected. topological sort can not be performed in graph with cycle
                    return "";
                }
            }
        }
        return builder.reverse().toString();
    }
    
    private boolean topological(Map<Character, Set<Character>> dict, char ch,
                               Map<Character, Boolean> visited,
                               Set<Character> dfsStack, StringBuilder builder) {
        if (dfsStack.contains(ch)) {
            // oops!! loop detected
            return false;
        }
        if (visited.get(ch)) {
            return true;
        }
        visited.put(ch, true);
        dfsStack.add(ch);
        for (char c : dict.getOrDefault(ch, new HashSet<>())) {
            if (!topological(dict, c, visited, dfsStack, builder)) {
                return false;
            }
        }
        builder.append(ch);
        dfsStack.remove(ch);
        return true;
    }
    
    private boolean fillDict(Map<Character, Set<Character>> dict, String a, String b) {
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                dict.computeIfAbsent(a.charAt(i), x -> new HashSet<>()).add(b.charAt(i));
                return true;
            }
        }
        // minimum word is prefix of larger word, make sure word with less length comes before larger word
        // why? because if one string is prefix of another then prefix must come before string for example,
        // we are here becasue a,b = "abc" or "ab", since a is lexicographically smaller than b then a must
        // be "ab" because "abc" can never come before "ab".
        return a.length() <= b.length(); 
    }

    public void sol1() {
    }


    public static void main(String[] args) {
        AlienDictionary1 t = new AlienDictionary1();
        t.sol1();
    }
}
