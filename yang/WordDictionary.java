import java.util.Map;

public class WordDictionary {

    private TrieNode root = new TrieNode();

    public WordDictionary() {
    }

    /**
     * Adds a word into the data structure
     * @param word
     */
    public void addWord(String word) {

        Map<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);

            TrieNode trie = null;
            if (children.containsKey(c)) {
                trie = children.get(c);
            }
            else {
                trie = new TrieNode(c);
                children.put(c, trie);
            }

            children = trie.children;

            if (i == word.length() - 1) {
                trie.isLeaf = true;
            }
        }
    }


    /**
     * Returns if the word is in the data structure. A word could
     * contain the dot character '.' to represent any one letter
     * @param word
     * @return
     */
    public boolean search(String word) {
        return dfsSearch(root.children, word, 0);
    }

    // 이해는 되지만
    // '.'부분은 코드를 좀더 세련되게 다음을 수 있을거 같다.
    public boolean dfsSearch(Map<Character, TrieNode> children, String word, int start) {

        if (start == word.length()) {
            if (children.size() == 0) {
                return true;
            } else {
                return false;
            }
        }

        char c = word.charAt(start);
        if (children.containsKey(c)) {

            if (start == word.length() - 1 && children.get(c).isLeaf) {
                return true;
            }
            return dfsSearch(children.get(c).children, word, start + 1);

        } else if (c == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child : children.entrySet()) {
                if (start == word.length() - 1 && child.getValue().isLeaf) {
                    return true;
                }
                // if any path is true, set result to be true;
                if (dfsSearch(child.getValue().children, word, start + 1)) {
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        }
    }

}