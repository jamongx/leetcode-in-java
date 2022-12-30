import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class NumberofConnectedComponentsinanUndirectedGraph {

    /**
     * https://walkccc.me/LeetCode/problems/0323/
     * BFS
     * @param n
     * @param edges
     * @return
     */
    public int sol1(int n, int[][] edges) {
        int ans = 0;
        List<Integer>[] graph = new List[n];
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < n; ++i) {
            if (!seen.contains(i)) {
                bfs(graph, i, seen);
                ++ans;
            }
        }

        return ans;
    }


    private void bfs(List<Integer>[] graph, int node, Set<Integer> seen) {
        Queue<Integer> q = new ArrayDeque<>(Arrays.asList(node));
        seen.add(node);

        while (!q.isEmpty()) {

            final int u = q.poll();
            for (final int v : graph[u]) {

                if (!seen.contains(v)) {
                    q.offer(v);
                    seen.add(v);
                }
            }
        }
    }


    /**
     * https://walkccc.me/LeetCode/problems/0323/
     * DFS
     * @param n
     * @param edges
     * @return
     */
    public int sol2(int n, int[][] edges) {
        int ans = 0;
        List<Integer>[] graph = new List[n];
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 0; i < n; ++i)
            if (seen.add(i)) {
                dfs(graph, i, seen);
                ++ans;
            }

        return ans;
    }

    private void dfs(List<Integer>[] graph, int u, Set<Integer> seen) {
        for (final int v : graph[u])
            if (seen.add(v))
                dfs(graph, v, seen);
    }

    public int sol3(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges)
            uf.unionByRank(edge[0], edge[1]);

        return uf.getCount();
    }

    public static void main(String[] args) {
        NumberofConnectedComponentsinanUndirectedGraph t = new NumberofConnectedComponentsinanUndirectedGraph();

        int n = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        System.out.println(t.sol1(n, edges1));
        System.out.println(t.sol1(n, edges2));
    }

}

/**
 * https://walkccc.me/LeetCode/problems/0323/
 */
class UnionFind {

    private int count;
    private int[] id;
    private int[] rank;
    
    public UnionFind(int n) {
        count = n;
        id = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }        

    public void unionByRank(int u, int v) {
        final int i = find(u);
        final int j = find(v);
        if (i == j) {
            return;
        }

        if (rank[i] < rank[j]) {
            id[i] = id[j];
        } else if (rank[i] > rank[j]) {
            id[j] = id[i];
        } else {
            id[i] = id[j];
            ++rank[j];
        }    
        --count;
    }    

    public int getCount() {
        return count;
    }    

    private int find(int u) {
        return id[u] == u ? u : (id[u] = find(id[u]));
    }
}