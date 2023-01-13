import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NumberofConnectedComponentsinanUndirectedGraph {

    /**
     * https://walkccc.me/LeetCode/problems/0323/
     * DFS
     */
    public int sol1(int n, int[][] edges) {
        
        // edges를 map에 리스트에 넣는다.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
        }
        
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // visited(set) 에 포함되어 있다면 false를 return한다.
            if (visited.add(i)) {
                dfs(i, map, visited);
                ++ans;
            }
        }

        return ans;
    }

    private void dfs(int i,
                     Map<Integer, List<Integer>> map,
                     Set<Integer> visited) {

        for (int j : map.get(i)) {
            if (visited.add(j)) {
                dfs(j, map, visited);
            }
        }
    }


    /**
     * https://walkccc.me/LeetCode/problems/0323/
     * BFS
     */
    public int sol2(int n, int[][] edges) {

        // edges를 map에 리스트에 넣는다.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // use queue to traverse the graph
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (visited.add(i)) {
                bfs(i, map, visited);
                ++ans;
            }
        }

        return ans;
    }

    private void bfs(int i,
                     Map<Integer, List<Integer>> map,
                     Set<Integer> visited) {

        Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(i));
        while (!queue.isEmpty()) {

            int head = queue.poll();
            for (int j : map.get(head)) {

                if (visited.add(j)) {
                    queue.offer(j);
                }
            }
        }
    }


    public static void main(String[] args) {
        NumberofConnectedComponentsinanUndirectedGraph t = new NumberofConnectedComponentsinanUndirectedGraph();

        int n = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        System.out.println(t.sol1(n, edges1));
        System.out.println(t.sol1(n, edges2));

        System.out.println(t.sol2(n, edges1));
        System.out.println(t.sol2(n, edges2));
    }

}
