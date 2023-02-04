package we;

import java.util.Arrays;

public class SetMatrixZeroes {

	public void setZeroes(int[][] matrix) {
		final int m = matrix.length; // row
		final int n = matrix[0].length; // col 
		boolean shouldFillFirstRow = false;
		boolean shouldFillFirstCol = false;

		for (int j = 0; j < n; ++j)
			if (matrix[0][j] == 0) {
				shouldFillFirstRow = true;
				break;
			}

		for (int i = 0; i < m; ++i)
			if (matrix[i][0] == 0) {
				shouldFillFirstCol = true;
				break;
			}

		// Store the information in the 1st row/col
		// 0의 셀을 찾아서 열,행의 첫번째 셀을 0으로 설정
		for (int i = 1; i < m; ++i)
			for (int j = 1; j < n; ++j)
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
		System.out.println(Arrays.deepToString(matrix));
		// Fill 0s for the matrix except the 1st row/col
		// 각 열과 행의 첫번째 값이 0이면, 해당 열과 행을 0으로 설정 
		for (int i = 1; i < m; ++i)
			for (int j = 1; j < n; ++j)
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
		System.out.println(Arrays.deepToString(matrix));
		// Fill 0s for the 1st row if needed
		// 첫 열에 0이 있으면 그열을 0으로 설정
		if (shouldFillFirstRow)
			for (int j = 0; j < n; ++j)
				matrix[0][j] = 0;
		System.out.println(Arrays.deepToString(matrix));
		// Fill 0s for the 1st col if needed
		// 첫 행에 0이 있으면 그행을 0으로 설정
		if (shouldFillFirstCol)
			for (int i = 0; i < m; ++i)
				matrix[i][0] = 0;
		System.out.println(Arrays.deepToString(matrix));		
	}

}
