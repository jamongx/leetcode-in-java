/*public class TrieNode4 {
    public TrieNode4[] children = new TrieNode4[26];
    public String item = "";
}*/

public class Trie extends ImplementTrie {

    /*public TrieNode4 root = new TrieNode4();

    public void insert(String word) {
        TrieNode4 node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode4();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;
    }

    public boolean search(String word) {
        TrieNode4 node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        if (node.item.equals(word)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        TrieNode4 node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return true;
    }*/
}

