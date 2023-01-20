import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://massivealgorithms.blogspot.com/2019/04/leetcode-269-alien-dictionary.html
 */
public class AlienDictionary {

    /**
     * Topological sort with BFS.
     * The tricky part is: how to construct the node-edge relationship?
     * For the same index of two strings: if the word1[index] != word2[index],
     * that means c1 is at the leading position than c2 in topological order.
     * 
     * Use this feature to build the edges. 
     * 
     * 1. Build graph
     * 2. Calculate indegree
     * 3. BFS
     * @param words
     * @return
     */
    public String alienOrderBFS(String[] words) {

        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegree = new int[26];

        buildGraph(words, graph, inDegree);

        String order = topologicalSort(graph, inDegree);
        return order.length() == graph.size() ? order : "";
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {
        // Create nodes
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new HashSet<>());
            }
        }

        // Build edges
        for (int i = 1; i < words.length; i++) {
            String first  = words[i - 1];
            String second = words[i];

            int length = Math.min(first.length(), second.length());
            System.out.println("first=" +first +", second=" +second +", max.len=" +length);

            for (int j = 0; j < length; j++) {
                char parent = first.charAt(j);
                char child = second.charAt(j);

                if (parent != child) {
                    
                    if (!graph.get(parent).contains(child)) {
                        graph.get(parent).add(child);
                        inDegree[child - 'a']++;
                        System.out.println("parent=" +parent +", child=" +child +", inDegree(" +child +")=" +inDegree[child - 'a']);
                    }
                    break;
                }
            }
        }
    }

    private String topologicalSort(Map<Character, Set<Character>> graph, int[] inDegree) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return sb.toString();
    }



    /* Thoughts:
     * DFS, mark visited. When dfs down to an leaf element,
     * it'll be the last element of the final output. (reverse order)
     */
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> visited = new HashMap<>();
    StringBuffer sb = new StringBuffer();

    public String alienOrderDFS(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // Build graph, and visited map.
        buildGraph(words);

        // Topological sort with dfs
        for (char c : graph.keySet()) {
            if (!dfs(c)) {
                return "";
            }
        }

        return sb.toString();
    }

    private void buildGraph(String[] words) {
        // Create nodes
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                    visited.put(c, 0);
                }
            }
        }

        // Build edges
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                char c1 = words[i].charAt(index);
                char c2 = words[i + 1].charAt(index);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
                index++;
            }
        }
    }

    private boolean dfs(Character c) {
        if (visited.get(c) == 1) {
            return true;
        }
        if (visited.get(c) == -1) {
            return false;
        }

        visited.put(c, -1);
        for (char edgeNode : graph.get(c)) {
            if (!dfs(edgeNode)) {
                return false;
            }
        }
        visited.put(c, 1);
        sb.insert(0, c); // leaf element, add to buffer
        return true;
    }


    public static void main(String[] args) {
        AlienDictionary t = new AlienDictionary();

        //String[] input = { "wrt", "wrf", "er", "ett", "rftt" };
        String[] input = { "ab", "adc" };

        System.out.println(t.alienOrderBFS(input));
        System.out.println(t.alienOrderDFS(input));
    }
}
