import java.util.Map;
import java.util.HashMap;

public class TrieNode {
    private Map<Character, TrieNode> children = new HashMap<>();
    private boolean isEnd;

    public TrieNode() {
    }

    public boolean containsKey(char ch) {
        return this.children.containsKey(ch);
    }

    public TrieNode get(char ch) {
        return this.children.get(ch);
    }

    public void put(char ch, TrieNode node) {
        this.children.put(ch, node);
    }

    public void setEnd() {
        this.isEnd = true;
    }

    public boolean isEnd() {
        return this.isEnd;
    }
}