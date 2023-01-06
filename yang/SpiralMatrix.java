import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> sol1(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

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

    public List<Integer> sol2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        final int m = matrix.length;
        final int n = matrix[0].length;
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
