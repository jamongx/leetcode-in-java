import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    
    public GraphNode(int val) {
        this.val = val;
    }
    
    public GraphNode(int val, List<GraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("val=").append(this.val);
        str.append(", neighbors=");
        Iterator<GraphNode> iter = this.neighbors.iterator();
        while (iter.hasNext()) {
            str.append(iter.next().val).append(" ");
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

        List<GraphNode> list = new ArrayList<GraphNode>();
        list.add(node2);
        list.add(node4);
        node1.neighbors = list;

        list = new ArrayList<GraphNode>();
        list.add(node1);
        list.add(node3);
        node2.neighbors = list;

        list = new ArrayList<GraphNode>();
        list.add(node2);
        list.add(node4);
        node3.neighbors = list;

        list = new ArrayList<GraphNode>();
        list.add(node3);
        list.add(node1);
        node4.neighbors = list;
        return node1;
    }

    // DFS
    public HashMap<GraphNode, GraphNode> map = new HashMap<>();
    public GraphNode sol1(GraphNode node) {
        map.put(node, new GraphNode(node.val, new ArrayList<>()));

        for (GraphNode neighbor : node.neighbors) {
            if (map.containsKey(neighbor)) {
                map.get(node).neighbors.add(map.get(neighbor));
            } else {
                map.get(node).neighbors.add(sol1(neighbor));
            }
        }
        return map.get(node);
    }

    // BFS
    public GraphNode sol2(GraphNode node) {
        Map<GraphNode, GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new ArrayDeque<>();

        queue.offer(node);
        map.put(node, new GraphNode(node.val, new ArrayList<>()));
        while (!queue.isEmpty()) {
            GraphNode h = queue.poll();

            for (GraphNode neighbor : h.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new GraphNode(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                map.get(h).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }

    public static void main(String[] args) {
        CloneGraph t = new CloneGraph();

        GraphNode node = t.buildGraph();

        System.out.println(t.sol1(node));
        //System.out.println(t.sol2(node));
    }

}
