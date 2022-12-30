import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> sol1(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        while (result.size() < m * n) {
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            // prevent duplicate row
            if (bottom < top)
                break;
            for (int j = right; j >= left; j--) {
                result.add(matrix[bottom][j]);
            }
            bottom--;
            // prevent duplicate column
            if (right < left)
                break;
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

    public ArrayList<Integer> sol2(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new ArrayList<Integer>();
        return spiralOrder(matrix, 0, 0, matrix.length, matrix[0].length);
    }

    public ArrayList<Integer> spiralOrder(int[][] matrix, int x, int y, int m, int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (m <= 0 || n <= 0)
            return result;
        // only one element left
        if (m == 1 && n == 1) {
            result.add(matrix[x][y]);
            return result;
        }
        // top - move right
        for (int i = 0; i < n - 1; i++) {
            result.add(matrix[x][y++]);
        }
        // right - move down
        for (int i = 0; i < m - 1; i++) {
            result.add(matrix[x++][y]);
        }
        // bottom - move left
        if (m > 1) {
            for (int i = 0; i < n - 1; i++) {
                result.add(matrix[x][y--]);
            }
        }
        // left - move up
        if (n > 1) {
            for (int i = 0; i < m - 1; i++) {
                result.add(matrix[x--][y]);
            }
        }
        if (m == 1 || n == 1)
            result.addAll(spiralOrder(matrix, x, y, 1, 1));
        else
            result.addAll(spiralOrder(matrix, x + 1, y + 1, m - 2, n - 2));
        return result;
    }


    public List<Integer> sol3(int[][] matrix) {
        if (matrix.length == 0)
            return new ArrayList<>();

        final int m = matrix.length;
        final int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int r1 = 0;
        int c1 = 0;
        int r2 = m - 1;
        int c2 = n - 1;

        // Repeatedly add matrix[r1..r2][c1..c2] to ans
        while (ans.size() < m * n) {
            for (int j = c1; j <= c2 && ans.size() < m * n; ++j)
                ans.add(matrix[r1][j]);
            for (int i = r1 + 1; i <= r2 - 1 && ans.size() < m * n; ++i)
                ans.add(matrix[i][c2]);
            for (int j = c2; j >= c1 && ans.size() < m * n; --j)
                ans.add(matrix[r2][j]);
            for (int i = r2 - 1; i >= r1 + 1 && ans.size() < m * n; --i)
                ans.add(matrix[i][c1]);
            ++r1;
            ++c1;
            --r2;
            --c2;
        }

        return ans;
    }
    
}
