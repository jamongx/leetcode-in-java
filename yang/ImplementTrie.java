
public class ImplementTrie {

    protected TrieNode root = new TrieNode();

    public ImplementTrie() {
    }

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode node = root; // 시작 trie는 root 이다.

        // loop를 돌면서 children은 sub trie로 계속 이어진다.
        for(char ch : word.toCharArray()) {

            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }

            // put을 하기 때문에, null exception은 발생하지 않는다.
            node = node.get(ch);
        }
        // 단어의 마지막 문자에 isLeaf를 true를 설정한다.
        node.setEnd();
    }

    // search와 startWith에서 call한다.
    public TrieNode searchNode(String word) {
        TrieNode node = root; // 시작 trie는 root 이다.

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

    // Returns if the word is in the trie
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
