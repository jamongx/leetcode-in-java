public class Trie extends ImplementTrie {

    /**
     * search a prefix or whole key in trie and
     * returns the node where search ends
     */
    public boolean search(String word) {
        return dfs(word, root);
    }

    private boolean dfs(String word, TrieNode node) {
        if (node == null) {
            return false;
        }

        if (word.length() == 0) {
            return node.isEnd();
        }

        char curr = word.charAt(0);
        if (curr == '.') {
            // any char 이므로 26 loop를 돌면서 각각 recursion을 실행한다
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                if (dfs(word.substring(1), node.get(c))) {
                    return true;
                }
            }
        }
        else if (node.containsKey(curr)) {
            return dfs(word.substring(1), node.get(curr));
        }
        return false;
    }


}

