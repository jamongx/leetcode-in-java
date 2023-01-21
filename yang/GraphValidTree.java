import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphValidTree {
    
    /**
     * https://www.programcreek.com/2014/05/graph-valid-tree-java/
     * DFS
     */
    public boolean sol1(int n, int[][] edges) {

        // edges를 map에 리스트에 넣는다.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        // node의 edge 정보를 list에 넣는다.
        // {{1,2}, {3,4}}
        // edge[0][0]: 1, edge[0][1]: 2
        // edge[1][0]: 3, edge[1][1]: 4
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        if (hasCycle(0, -1, map, visited)) {
            return false;
        }

        // visitied 중에 false가 있다면 연결되지 않은 섬이 있다.
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Cycle이 있는지 알아낸다.
     * @param curr   start  node
     * @param parent parent node -> recursion을 돌때 curr가 parrent가 된다.
     * @return
     * 1. visited[curr]가 true이면 이미 한번 방문했으므로 false를 return 한다.
     * 2. curr의 연결된 노드에 대해서 helper 실행 결과가 false이면 false를 return한다.
     * 3. 아니면 true를 return한다.
     */
    public boolean hasCycle(int curr, int parent,
                          Map<Integer, List<Integer>> map,
                          boolean[] visited) {

        if (visited[curr]) {
            return true;
        }

        visited[curr] = true;
        for (int i : map.get(curr)) {

            // (i != parent) -> curr가 parent가 아니고
            // hasCycle(i, curr) 가 false이면
            // curr -> parent
            if (i != parent && hasCycle(i, curr, map, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://www.programcreek.com/2014/05/graph-valid-tree-java/
     * BFS
     */
    public boolean sol2(int n, int[][] edges) {

        // edges를 map에 리스트에 넣는다.
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        // build the graph
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // use queue to traverse the graph
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(0);

        while (!queue.isEmpty()) {

            int head = queue.poll();
            if (visited.contains(head)) {
                return false;
            }

            visited.add(head);

            List<Integer> list = map.get(head);
            for (int i : list) {
                if (!visited.contains(i)) {
                    queue.offer(i);
                }
            }
        }

        if (visited.size() < n) {
            return false;
        }
        return true;
    }


    /**
     * https://walkccc.me/LeetCode/problems/0261/
     * Approach 2: UF
     * Time: O(nlog⁡∗n)
     * space: O(n)
     */
    public boolean sol3(int n, int[][] edges) {
        if (n == 0 || edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.unionByRank(edge[0], edge[1]);
        }

        return uf.getCount() == 1;
    }


    public static void main(String[] args) {

        GraphValidTree t = new GraphValidTree();

        int nodes1 = 5;
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};   
        System.out.println(t.sol3(nodes1, edges1));

        int nodes2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(t.sol3(nodes2, edges2));
    }
}
