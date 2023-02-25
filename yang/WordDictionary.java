
public class WordDictionary {

    private Trie trie = new Trie();

    public WordDictionary() {
    }

    /**
     * Adds a word into the data structure
     */
    public void addWord(String word) {
        this.trie.insert(word);
    }


    /**
     * Returns if the word is in the data structure. A word could
     * contain the dot character '.' to represent any one letter
     */
    public boolean search(String word) {
        // trie.search method를 봐야 됩니다.
        return this.trie.search(word);
    }

    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println("wordDictionary.search(\"pad\")="
            +wordDictionary.search("pad")); // return False
        System.out.println("wordDictionary.search(\"bad\")="
            +wordDictionary.search("bad")); // return True
        System.out.println("wordDictionary.search(\".ad\")="
            +wordDictionary.search(".ad")); // return True
        System.out.println("wordDictionary.search(\"b..\")="
            +wordDictionary.search("b..")); // return True
    }

}