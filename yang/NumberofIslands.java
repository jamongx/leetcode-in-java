import java.util.ArrayDeque;
import java.util.Queue;

public class NumberofIslands {

    /**
     * DFS
     * @param grid
     * @return
     */
    public int sol1(char[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    ++ans;
                }
            }
        }

        return ans;
    }

    private void DFS(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2'; // Mark '2' as visited
        DFS(grid, i + 1, j);
        DFS(grid, i - 1, j);
        DFS(grid, i, j + 1);
        DFS(grid, i, j - 1);
    }


    /**
     * BFS
     * @param grid
     * @return
     */
    public int sol2(char[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    BFS(grid, i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }


    private int[] dirs = { 0, 1, 0, -1, 0 };

    private void BFS(char[][] grid, int row, int col) {

        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { row, col });
        grid[row][col] = '2'; // Mark '2' as visited

        while (!q.isEmpty()) {

            // peek: 맨 앞에 있는 값 반환, 비어있을 경우 null 반환
            int i = q.peek()[0];
            // poll: 맨 앞에 있는 값 반환 후 삭제 큐가 비어있을 경우 null 반환
            int j = q.poll()[1];

            for (int k = 0; k < 4; k++) {

                int x = i + dirs[k];
                int y = j + dirs[k + 1];

                if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) {
                    continue;
                }

                if (grid[x][y] != '1') {
                    continue;
                }

                // 맨 뒤에 값 삽입
                q.offer(new int[] { x, y });
                grid[x][y] = '2'; // Mark '2' as visited
            }
        }
    }


    public static void main(String[] args) {

        char[][] grid1 = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };
        
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        
        NumberofIslands t = new NumberofIslands();
        System.out.println(t.sol1(grid1));
        System.out.println(t.sol1(grid2));

    }
}
