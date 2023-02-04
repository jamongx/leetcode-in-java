public class RotateImage {

    /**
     * Indexing
     * TC: O(n^2)
     * SC: O(1)
     * matrix[i][j] = matrix[n-1-j][i]
     * 밖 -> 안으로 들어가면서 rotate 해준다.
     */
    public void sol1(int[][] matrix) {

        // layer의 개수를 결정한다.
        for (int min = 0; min < matrix.length / 2; min++) {

            // min과 max는 rotation해줘야 되는 matrix 한줄의 시작과 끝을 의미한다.
            int max = matrix.length - min - 1;

            System.out.println("----- min=" +min +", max=" +max);

            for (int i = min; i < max; i++) {

                int offset = i - min;
                int top                   = matrix[min][i];
                matrix[min][i]            = matrix[max - offset][min];
                matrix[max - offset][min] = matrix[max][max - offset];
                matrix[max][max - offset] = matrix[i][max];
                matrix[i][max]            = top;
                System.out.println("-----------");
                Utils.print2Darray(matrix);
            }
        }
    }

    /*
     * Reverse
     * TC: O(n^2)
     * SC: O(1)
     * matrix[i][j] = matrix[n-1-j][i]
     * 1. row 를 바꿔주고 matrix[1]<->matrix[4], matrix[2]<->matrix[3]
     * 2. 대각선으로 matrix[i][j] <-> matrix[j][i] 들을 바꿔준다.
     */
    public void sol2(int[][] matrix) {

        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
            System.out.println("-----------");
            Utils.print2Darray(matrix);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
                System.out.println("-----------");
                Utils.print2Darray(matrix);
            }
        }
    }



    public static void main(String[] args) {
        RotateImage t = new RotateImage();

        int[][] matrix = { {  5,  1,  9, 11 },
                           {  2,  4,  8, 10 },
                           { 13,  3,  6,  7 },
                           { 15, 14, 12, 16 } };

        System.out.println("original -----------");
        Utils.print2Darray(matrix);
        t.sol1(matrix);
    }

}
