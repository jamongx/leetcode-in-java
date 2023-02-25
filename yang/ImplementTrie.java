
public class ImplementTrie {

    protected TrieNode root = new TrieNode();

    public ImplementTrie() {
    }

    public void insert(String word) {
        TrieNode node = this.root;

        for(char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();// set a flag to the last of word
    }


    public TrieNode searchNode(String word) {
        TrieNode node = this.root;
        for(char ch : word.toCharArray()) {
            if (node.containsKey(ch)) {
                node = node.get(ch);
            }
            else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return (node != null && node.isEnd());
    }

    // Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return (node != null);
    }

    public static void main(String[] args) {
        ImplementTrie trie = new ImplementTrie();
        trie.insert("apple");
        System.out.println("search->apple=" +trie.search("apple"));
        System.out.println("search->app=" +trie.search("app"));
        System.out.println("startWith->app=" +trie.startsWith("app"));
        trie.insert("app");
        System.out.println("search->app=" +trie.search("app"));
    }
}
