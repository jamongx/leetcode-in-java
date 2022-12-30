import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
// UndirectedGraphNode
class GraphNode {
    public int label;
    public List<GraphNode> neighbors = new ArrayList<GraphNode>();
    
    public GraphNode(int label) {
        this.label = label;
    }
    
    public GraphNode(int label, List<GraphNode> neighbors) {
        this.label = label;
        this.neighbors = neighbors;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("label=").append(this.label);
        str.append(", neighbors=");
        Iterator<GraphNode> iter = this.neighbors.iterator();
        while (iter.hasNext()) {
            str.append(iter.next().label).append(" ");
        }
        return str.toString();
    }
}

public class CloneGraph {

    /* Build the desired graph
     Note : All the edges are Undirected Given Graph:
      1--2
      |  |
      4--3 */
    public GraphNode buildGraph() {
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);

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


    // DFS
    public GraphNode sol1(GraphNode node) {
        if (node == null) {
            return null;
        }
        
        // copy 된 map을 저장하여 무한히 동작하지 않도록 한다.
        Map<GraphNode, GraphNode> map = new HashMap<>();
        return DFS(node, map);
    }

    private GraphNode DFS(GraphNode node, Map<GraphNode, GraphNode> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        GraphNode copy = new GraphNode(node.label);
        map.put(node, copy);
        for (GraphNode neighbor : node.neighbors) {
            copy.neighbors.add(DFS(neighbor, map));
        }
        System.out.println("DFS=" +copy);
        return copy;
    }


    // BFS
    public GraphNode sol2(GraphNode node) {

        // copy할 key로 저장하고 copy한 value를 return 한다.
        Map<GraphNode, GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new ArrayDeque<>();

        // add node retrun true/false
        queue.offer(node);
        map.put(node, new GraphNode(node.label));

        while (!queue.isEmpty()) {
            // The method returns head element and also removes it
            GraphNode head = queue.poll();

            for (GraphNode neighbor : head.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new GraphNode(neighbor.label));
                    queue.offer(neighbor);
                }
                map.get(head).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }

    public static void main(String[] args) {
        CloneGraph t = new CloneGraph();

        GraphNode node = t.buildGraph();

        //System.out.println(t.sol1(node));
        System.out.println(t.sol2(node));
    }

}
