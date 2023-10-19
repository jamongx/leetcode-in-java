public class WordSearch {

    public boolean sol1(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int wordPos) {
        if (wordPos == word.length() - 1) { // s와 word의 길이가 같으면
            return true;
        }

        // i가 negative이거나 board 보다 큰 값(보통 len-1) 을 가지면
        if (i < 0 || i == board.length
         || j < 0 || j == board[0].length) {
            return false;
        }

        
        if (board[i][j] != word.charAt(wordPos) // word sth번째 char와 board[i][j]가 다르면
         || board[i][j] == '*') { // board[i][j] -> lock
            return false;
        }


        char cache = board[i][j];
        board[i][j] = '*'; // lock
        boolean isExist = dfs(board, word, i + 1, j, wordPos + 1) || // east, s를 1증가시킴
                          dfs(board, word, i - 1, j, wordPos + 1) || // west
                          dfs(board, word, i, j + 1, wordPos + 1) || // north
                          dfs(board, word, i, j - 1, wordPos + 1);   // south
        board[i][j] = cache; // unlock

        return isExist;
    }

    public static void main(String[] args) {
        WordSearch t = new WordSearch();
        
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};

        String word = "ABCCED";

        System.out.println(t.sol1(board, word));
    }

}
