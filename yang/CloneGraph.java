import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

public class CloneGraph {

    // DFS
    public UndirectedGraphNode sol1(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        
        // copy 된 node을 저장하여 무한 loop를 막는다.
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // dfs로 복사된 node를 return한다.
        return DFS(node, map);
    }

    private UndirectedGraphNode DFS(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        UndirectedGraphNode copy1 = new UndirectedGraphNode(node.label);
        map.put(node, copy1);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            copy1.neighbors.add(DFS(neighbor, map));
        }
        System.out.println("DFS=" +copy1);
        return copy1;
    }


    // BFS
    public UndirectedGraphNode sol2(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();

        // original node를 key로 저장하고 copy한 node value로 넣어서
        // 마지막에 input(node)의 value값을 return 한다.
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        // add node return true/false
        queue.offer(node);
        System.out.println("que] add node=" +node);

        UndirectedGraphNode copy1 = new UndirectedGraphNode(node.label);
        map.put(node, copy1);
        System.out.println("map] put  key=" +node +", copy1=" +copy1);

        while (!queue.isEmpty()) {

            // The method returns head element and also removes it
            UndirectedGraphNode head = queue.poll();
            System.out.println("que] get head=" +head);

            for (UndirectedGraphNode neighbor : head.neighbors) {

                if (!map.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    System.out.println("que] add node=" +node);
                    UndirectedGraphNode copy2 = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, copy2);
                    System.out.println("map] put  key=" +node +", copy2=" +copy2);
                }
                map.get(head).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
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
