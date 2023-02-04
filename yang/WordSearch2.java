import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class WordSearch2 {

    private Trie trie = new Trie();
    private Set<String> result = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words) {
            this.trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        // temporary visit lock(flag)
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j);
            }
        }

        return new ArrayList<String>(result);
    }

    private void dfs(char[][] board, boolean[][] visited, String str, int i, int j) {
        int m = board.length;
        int n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }

        if (visited[i][j]) {
            System.out.println("dfs) skip str=" +str +", visited[" +i +"][" +j +"]=" +visited[i][j]);
            return;
        }
        
        str = str + board[i][j];
        System.out.println("dfs) str=" + str);
        
        // str로 시작하는 단어가 없으면 return
        if (!this.trie.startsWith(str)) {
            System.out.println("dfs) not start with str=" + str);
            return;
        }
        
        // tri에 포함되어 있으면 add
        if (this.trie.search(str)) {
            System.out.println("dfs) add to trie, str=" + str);
            result.add(str);
        }
        
        visited[i][j] = true;
        System.out.println("dfs) str=" +str +", board[" +i +"][" +j +"]=" +board[i][j] +", visited[" +i +"][" +j +"]=" +visited[i][j]);
        
        dfs(board, visited, str, i - 1, j);
        dfs(board, visited, str, i + 1, j);
        dfs(board, visited, str, i, j - 1);
        dfs(board, visited, str, i, j + 1);
        
        // i, j에 대해서 검색이 끝나고 다시 false로 set해야
        // 다른 i*, j*에 대해서 검색을 시작할 수 있기 때문에
        visited[i][j] = false;
        System.out.println("str=" +str +", board[" +i +"][" +j +"]=" +board[i][j] +", visited[" +i +"][" +j +"]=" +visited[i][j]);
    }

    public static void main(String[] args) {
        WordSearch2 t = new WordSearch2();

        char[][] board = {{ 'o', 'a', 'a', 'n' },
                          { 'e', 't', 'a', 'e' },
                          { 'i', 'h', 'k', 'r' },
                          { 'i', 'f', 'l', 'v' }};

        String[] words = {"oath", "pea", "eat", "rain"};


        System.out.println(t.findWords(board, words));
    }

}
