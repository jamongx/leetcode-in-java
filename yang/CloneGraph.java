import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

public class CloneGraph {

    // <org, copy>
    private Map<UndirectedGraphNode, UndirectedGraphNode> map_dfs = new HashMap<>();

    // DFS
    public UndirectedGraphNode sol1(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        if (map_dfs.containsKey(node)) {
            return map_dfs.get(node);
        }

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map_dfs.put(node, copy);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            copy.neighbors.add(sol1(neighbor));
        }

        System.out.println("DFS=" +copy);
        return copy;
    }

    
    // <org, copy>
    private Map<UndirectedGraphNode, UndirectedGraphNode> map_bfs = new HashMap<>();

    /**
     * BFS 
     */
    public UndirectedGraphNode sol2(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        queue.offer(node);

        UndirectedGraphNode copy1 = new UndirectedGraphNode(node.label);
        map_bfs.put(node, copy1);

        while (!queue.isEmpty()) {

            UndirectedGraphNode head = queue.poll();

            for (UndirectedGraphNode neighbor : head.neighbors) {

                if (!map_bfs.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    UndirectedGraphNode copy2 = new UndirectedGraphNode(neighbor.label);
                    map_bfs.put(neighbor, copy2);
                }
                map_bfs.get(head).neighbors.add(map_bfs.get(neighbor));
            }
        }

        return map_bfs.get(node);
    }

    /* Build the desired graph
     Note : All the edges are Undirected Given Graph:
      1--2
      |  |
      4--3 */
    public UndirectedGraphNode buildGraph() {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node3);

        node4.neighbors.add(node3);
        node4.neighbors.add(node1);

        return node1;
    }



    public static void main(String[] args) {
        CloneGraph t = new CloneGraph();

        UndirectedGraphNode node = t.buildGraph();
        System.out.println("Built graph=");
        Utils.printUGraph(node);

        //Utils.printUGraph(t.sol1(node));
        Utils.printUGraph(t.sol2(node));
    }

}
