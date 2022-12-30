public class RotateImage {
   
    public void sol1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public void sol2(int[][] matrix) {
        for (int i = 0, j = matrix.length - 1; i < j; ++i, --j) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }

        for (int i = 0; i < matrix.length; ++i)
            for (int j = i + 1; j < matrix.length; ++j) {
                final int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
    }

    public void sol3(int[][] matrix) {
        for (int min = 0; min < matrix.length / 2; ++min) {
            final int max = matrix.length - min - 1;
            for (int i = min; i < max; ++i) {
                final int offset = i - min;
                final int top = matrix[min][i];
                matrix[min][i] = matrix[max - offset][min];
                matrix[max - offset][min] = matrix[max][max - offset];
                matrix[max][max - offset] = matrix[i][max];
                matrix[i][max] = top;
            }
        }
    }

}
