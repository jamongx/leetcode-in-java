import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors = new ArrayList<>();
    
    public UndirectedGraphNode(int label) {
        this.label = label;
    }
    
    public UndirectedGraphNode(int label, List<UndirectedGraphNode> neighbors) {
        this.label = label;
        this.neighbors = neighbors;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("label=").append(this.label);
        str.append(", neighbors=");
        Iterator<UndirectedGraphNode> iter = this.neighbors.iterator();
        while (iter.hasNext()) {
            str.append(iter.next().label).append(" ");
        }
        return str.toString();
    }
}
