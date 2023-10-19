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
        

        int result = 0;
        Set<Integer> visited = new HashSet<>();
        // 모든 점 (node)에 대해서 loop를 돌려준다.
        for (int i = 0; i < n; i++) {
            // add가 된다면 true 이미 set에 있으면 false
            if (visited.add(i)) {
                dfs(i, map, visited);
                ++result;
            }
        }

        return result;
    }

    // i와 연결된 j(node)들을 set에 넣어준다.
    private void dfs(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {

        // i와 연결된 j(node)들로 loop를 돌린다.
        for (int j : map.get(i)) {
            // add가 된다면 true 이미 set 에 있으면 false
            if (visited.add(j)) {
                // j와 연결된 k(node)들을 set에 넣어준다.
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
        int result = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (visited.add(i)) { // add가 되면 result++이 된다.
                bfs(i, map, visited);
                ++result;
            }
        }

        return result;
    }

    private void bfs(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {

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


    /**
     * https://walkccc.me/LeetCode/problems/0323/
     * UF
     */
    public int sol3(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.unionByRank(edge[0], edge[1]);
        }

        return uf.getCount();
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
