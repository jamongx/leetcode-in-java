package we;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {
	HashMap<Node, Node> map = new HashMap<>();
	public Node cloneGraph(Node node) {
	    map.put(node, new Node(node.val, new ArrayList<>()));
	 
	    for(Node neighbor: node.neighbors){
	        if(map.containsKey(neighbor)){
	            map.get(node).neighbors.add(map.get(neighbor)); 
	        }else{
	            map.get(node).neighbors.add(cloneGraph(neighbor));
	        }
	    }
	 
	    return map.get(node);
	}
}


class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
    	val =0;
    	neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
    	val = _val;
    	neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
    	val = _val;
    	neighbors = _neighbors;
    }
}
