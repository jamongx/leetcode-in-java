import java.util.Map;

public class ImplementTrie {

    private TrieNode root = new TrieNode();

    public ImplementTrie() {
    }

    /**
     * Inserts a word into the trie
     * @param word
     */
    public void insert(String word) {

        // 시작 trie는 root 이다.
        Map<Character, TrieNode> children = root.children;

        // loop를 돌면서 children은 sub trie로 계속 이어진다.
        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);
            if (!children.containsKey(c)) {
                children.put(c, new TrieNode(c));
            }

            TrieNode trie = children.get(c);

            // 연달아서 들어가야 하기 때문에
            children = trie.children;

            // 단어의 마지막 문자에 isLeaf를 true를 설정한다.
            if (i == word.length() - 1) {
                trie.isLeaf = true;
            }
        }
    }

    /**
     * Returns if the word is in the trie
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode trie = searchNode(word);
        return (trie != null && trie.isLeaf);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return (searchNode(prefix) != null);
    }


    public TrieNode searchNode(String str) {

        Map<Character, TrieNode> children = root.children;

        TrieNode trie = null;
        for(char c : str.toCharArray()) {
            if (!children.containsKey(c)) {
                return null;
            }
            else {
                trie = children.get(c);
                children = trie.children;
            }
        }
        return trie;
    }
}
