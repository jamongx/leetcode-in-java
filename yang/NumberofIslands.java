import java.util.ArrayDeque;
import java.util.Queue;

public class NumberofIslands {

    /**
     * DFS
     */
    public int sol1(char[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++ans;
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return;
        }

        if (grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2'; // Mark '2' as visited
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }


    /**
     * BFS
     */
    public int sol2(char[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    // TODO 동서남북 포함해서 Cell Class를 가독성 높에 바꿀수 있을것 같다.
    class Cell {
        int i;
        int j;
        Cell (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private int[] dirs = { 0, 1, 0, -1, 0 };

    private void bfs(char[][] grid, int row, int col) {

        Queue<Cell> queue = new ArrayDeque<>();

        queue.offer(new Cell(row, col));
        grid[row][col] = '2'; // Mark '2' as visited

        while (!queue.isEmpty()) {

            // peek: 맨 앞에 있는 값 반환, 비어있을 경우 null 반환
            // poll: 맨 앞에 있는 값 반환 후 삭제 큐가 비어있을 경우 null 반환
            Cell cell = queue.poll();

            for (int k = 0; k < 4; k++) {

                int x = cell.i + dirs[k];
                int y = cell.j + dirs[k + 1];

                if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) {
                    continue;
                }

                if (grid[x][y] != '1') {
                    continue;
                }

                // 맨 뒤에 값 삽입
                queue.offer(new Cell(x, y));
                grid[x][y] = '2'; // Mark '2' as visited
            }
        }
    }


    public static void main(String[] args) {
        NumberofIslands t = new NumberofIslands();

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
        
        System.out.println(t.sol2(grid1));
        System.out.println(t.sol2(grid2));

    }
}
