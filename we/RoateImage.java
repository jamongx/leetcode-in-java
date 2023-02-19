package we;

public class RoateImage {
	public void rotate(int[][] matrix) {
		// 루프를 돌면서 배열의 행을 뒤집어줌
		for (int i = 0, j = matrix.length - 1; i < j; ++i, --j) {
			int[] temp = matrix[i];
			matrix[i] = matrix[j];
			matrix[j] = temp;
		}
		// 각 행과 열의 셀의 값을 열과 행의 셀의 값으로 치환해줌.
		for (int i = 0; i < matrix.length; ++i)
			for (int j = i + 1; j < matrix.length; ++j) {
				final int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
	}
}
