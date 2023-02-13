package we;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {
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
			// Top
			for (int j = c1; j <= c2 && ans.size() < m * n; ++j)
				ans.add(matrix[r1][j]);
			System.out.println(Arrays.toString(ans.toArray()));
			// Right
			for (int i = r1 + 1; i <= r2 - 1 && ans.size() < m * n; ++i)
				ans.add(matrix[i][c2]);
			System.out.println(Arrays.toString(ans.toArray()));
			// Bottom
			for (int j = c2; j >= c1 && ans.size() < m * n; --j)
				ans.add(matrix[r2][j]);
			System.out.println(Arrays.toString(ans.toArray()));
			// Left
			for (int i = r2 - 1; i >= r1 + 1 && ans.size() < m * n; --i)
				ans.add(matrix[i][c1]);
			System.out.println(Arrays.toString(ans.toArray()));
			++r1;
			++c1;
			--r2;
			--c2;
		}

		return ans;
	}

}
