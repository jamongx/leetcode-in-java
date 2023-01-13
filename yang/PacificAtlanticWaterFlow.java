import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {

    /**
     * https://walkccc.me/LeetCode/problems/0417/ 
     * DFS
     * @param heights
     * @return
     */
    public List<List<Integer>> sol1(int[][] heights) {

        final int m = heights.length;
        final int n = heights[0].length;
        
        boolean[][] seenP = new boolean[m][n];
        boolean[][] seenA = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            dfs(heights, i, 0, 0, seenP);
            dfs(heights, i, n - 1, 0, seenA);
        }
        
        for (int j = 0; j < n; ++j) {
            dfs(heights, 0, j, 0, seenP);
            dfs(heights, m - 1, j, 0, seenA);
        }
        
        // seenP와 seenA가 모두 true이면 list에 저장하여 return한다.
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seenP[i][j] && seenA[i][j]) {
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] heights, int i, int j, int h, boolean[][] seen) {
        if (i < 0 || i == heights.length || j < 0 || j == heights[0].length) {
            return;
        }

        if (seen[i][j] || heights[i][j] < h) {
            return;
        }

        seen[i][j] = true;
        dfs(heights, i + 1, j, heights[i][j], seen); // east
        dfs(heights, i - 1, j, heights[i][j], seen); // west
        dfs(heights, i, j + 1, heights[i][j], seen); // south
        dfs(heights, i, j - 1, heights[i][j], seen); // north
    }


    class Cell {
        int i;
        int j;
        Cell (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    /**
     * https://walkccc.me/LeetCode/problems/0417/ 
     * BFS
     * @param heights
     * @return
     */
    public List<List<Integer>> sol2(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] seenP = new boolean[m][n];
        boolean[][] seenA = new boolean[m][n];
        
        Queue<Cell> qP = new ArrayDeque<>();
        Queue<Cell> qA = new ArrayDeque<>();

        for (int i = 0; i < m; ++i) {
            qP.offer(new Cell(i, 0));
            qA.offer(new Cell(i, n - 1));
            seenP[i][0] = true;
            seenA[i][n - 1] = true;
        }
        
        for (int j = 0; j < n; ++j) {
            qP.offer(new Cell(0, j));
            qA.offer(new Cell(m - 1, j));
            seenP[0][j] = true;
            seenA[m - 1][j] = true;
        }
        
        bfs(heights, qP, seenP);
        bfs(heights, qA, seenA);
        
        List<List<Integer>> ans = new ArrayList<>();
        // 전제 지도 (matrix)의 cell을 돌면서
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seenP[i][j] && seenA[i][j]) {
                    ans.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }

        return ans;
    }

    // directions
    private static int[] dirs = { 0, 1, 0, -1, 0 };

    private void bfs(int[][] heights, Queue<Cell> queue, boolean[][] seen) {

        while (!queue.isEmpty()) {

            Cell cell = queue.poll();

            // 동서남북 4개의 방향으로 height를 계산한다.
            for (int k = 0; k < 4; ++k) {
                int x = cell.i + dirs[k];
                int y = cell.j + dirs[k + 1];

                //System.out.println("cell.i=" +cell.i +" + dir[" +k +"]=" +dirs[k] +" --> x=" +x);
                //System.out.println("cell.j=" +cell.j +" + dir[" +k +"+1]=" +dirs[k+1] +" --> y=" +y);

                // x, y값이 matrix 범위를 넘어가면
                if (x < 0 || x == heights.length || y < 0 || y == heights[0].length) {
                    continue;
                }

                // 이미 방문을 했거나 height가 낮으면
                if (seen[x][y] || heights[x][y] < heights[cell.i][cell.j]) {
                    continue;
                }

                queue.offer(new Cell(x, y));
                seen[x][y] = true;
            }
        }
    }


    public static void main(String[] args) {

        PacificAtlanticWaterFlow t = new PacificAtlanticWaterFlow();

        int[][] heights = { { 1, 2, 2, 3, 5 },
                            { 3, 2, 3, 4, 4 },
                            { 2, 4, 5, 3, 1 },
                            { 6, 7, 1, 4, 5 },
                            { 5, 1, 1, 2, 4 } };
        
        System.out.println(t.sol1(heights));
        System.out.println(t.sol2(heights));

    }
}
