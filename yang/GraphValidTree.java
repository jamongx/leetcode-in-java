import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * https://www.programcreek.com/2014/05/graph-valid-tree-java/
 */
public class GraphValidTree {

    /**
     * 
     * @param n: The number of nodes
     * @param edges:
     * @return true -> tree, false -> not tree
     */
    public boolean validTreeDFS(int n, int[][] edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            map.put(i, list);
        }

        // edge[0] 과 edge[1]은 node 2개이다.
        // 각 node와 연결된 다른 node를 list에 넣는다.
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // loop가 있는지 알아낸다.
        boolean[] visited = new boolean[n];
        if (!helper(0, -1, map, visited)) {
            return false;
        }

        // 방문하지 않은 node가 있다면 tree가 아니다.
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    /*
     * loop가 있는지 알아내는 function
     * curr -> start node
     * parent -> parent node -> recursion을 돌때 curr가 parrent가 된다.
     * return
     *     1. visited[curr]가 true이면 이미 한번 방문했으므로 false를 return 한다.
     *     2. curr의 연결된 노드에 대해서 helper 실행 결과가 false이면 false를 return한다.
     *     3. 아니면 true를 return한다.
     */
    public boolean helper(int curr, int parent, Map<Integer, List<Integer>> map, boolean[] visited) {
        if (visited[curr]) {
            return false;
        }

        visited[curr] = true; // <----
        for (int i : map.get(curr)) {

            // i -> curr와 연결된 node
            // curr -> start node
            // (i != parent) -> curr와 연결된 node가 parent와 다르고
            // helper(i, curr) 가 false이면
            if (i != parent && !helper(i, curr, map, visited)) {
                return false;
            }
        }
        return true;
    }


    public boolean validTree(int n, int[][] edges) {
        List<Set<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int curNode = queue.remove();

            // found loop
            if (visited[curNode]) {
                return false;
            }

            visited[curNode] = true;

            for (int nextNode : adjList.get(curNode)) {
                queue.add(nextNode);
                adjList.get(nextNode).remove(curNode);
            }
        }

        for (boolean e : visited) {
            if (!e) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://walkccc.me/LeetCode/problems/0261/
     * @param n
     * @param edges
     * @return
     */
    public boolean validTreeBFS(int n, int[][] edges) {
        if (n == 0 || edges.length != n - 1) {
            return false;
        }
    
        List<Integer>[] graph = new List[n]; // -> warning
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }


        // 초기값 0        
        Queue<Integer> q = new ArrayDeque<>(Arrays.asList(0));
        Set<Integer> seen = new HashSet<>(Arrays.asList(0));
        while (!q.isEmpty()) {

          final int u = q.poll();

          for (final int v : graph[u]) {
              if (!seen.contains(v)) {
                q.offer(v);
                seen.add(v);
              }
          }
          }
    
        return seen.size() == n;
      }

    /**
     * 정답이 아니란다.
     * @param n
     * @param edges
     * @return
     */
    public boolean validTreeBFS2(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        // build the graph
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        // use queue to traverse the graph
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int head = q.poll();
            if (visited.contains(head)) {
                return false;
            }
            visited.add(head);
            ArrayList<Integer> vList = list.get(head);
            for (int v : vList) {
                if (!visited.contains(v)) {
                    q.offer(v);
                }
            }
        }
        if (visited.size() < n) {
            return false;
        }
        return true;
    }

}
