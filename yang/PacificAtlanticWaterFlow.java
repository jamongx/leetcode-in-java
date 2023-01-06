import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {

    /**
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
        dfs(heights, i + 1, j, heights[i][j], seen);// east
        dfs(heights, i - 1, j, heights[i][j], seen);// west
        dfs(heights, i, j + 1, heights[i][j], seen);// south
        dfs(heights, i, j - 1, heights[i][j], seen);// north
    }


    /**
     * BFS
     * @param heights
     * @return
     */
    public List<List<Integer>> sol2(int[][] heights) {

        final int m = heights.length;
        final int n = heights[0].length;

        Queue<int[]> qP = new ArrayDeque<>();
        Queue<int[]> qA = new ArrayDeque<>();

        boolean[][] seenP = new boolean[m][n];
        boolean[][] seenA = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            qP.offer(new int[] { i, 0 });
            qA.offer(new int[] { i, n - 1 });
            seenP[i][0] = true;
            seenA[i][n - 1] = true;
        }
        
        for (int j = 0; j < n; ++j) {
            qP.offer(new int[] { 0, j });
            qA.offer(new int[] { m - 1, j });
            seenP[0][j] = true;
            seenA[m - 1][j] = true;
        }
        
        bfs(heights, qP, seenP);
        bfs(heights, qA, seenA);
        
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

    private static final int[] dirs = { 0, 1, 0, -1, 0 };

    private void bfs(int[][] heights, Queue<int[]> q, boolean[][] seen) {

        while (!q.isEmpty()) {
            final int i = q.peek()[0];
            final int j = q.poll()[1];
            final int h = heights[i][j];

            for (int k = 0; k < 4; ++k) {
                final int x = i + dirs[k];
                final int y = j + dirs[k + 1];

                if (x < 0 || x == heights.length || y < 0 || y == heights[0].length) {
                    continue;
                }

                if (seen[x][y] || heights[x][y] < h) {
                    continue;
                }

                q.offer(new int[] { x, y });
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
        
        List<List<Integer>> res = t.sol1(heights);

        Iterator<List<Integer>> iter = res.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}
