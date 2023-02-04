public class SetMatrixZeroes {

    // setting 해주는건 알겠다
    // 순서를 이해해야 한다.
    // 첫번째 column과 row를 먼저 0로 설정하면
    // matrix가 전부 0이 되어버린다. -> 코드 순서를 바꿔보니 전부는 아니고 빠지는것도 있네
    public void sol1(int[][] matrix) {
        
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
        
        // first row에 0을 포함하고 있는지 확인한다.
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }
        
        // first column에 0을 포함하고 있는지 확인한다.
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Scan rest of the matrix,
        // Set the first number as 0 for every row and column if matrix[i][j] == 0 
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        System.out.println("set first row and first column ---------");
        Utils.print2Darray(matrix);

        // Use mark to set elements
        // Fill the rows with zeros by scanning the first column
        // Note that starting from matrix[1][0]
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println("fill zero with first row and first column that are zero ---------");
        Utils.print2Darray(matrix);

        // firstColumnZero가 true라면 (first column이 0을 포함하고 있다면)
        // firstColumn을 모두 zero로 설정해준다.
        if (firstColumnZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        System.out.println("set zero to first row --------");
        Utils.print2Darray(matrix);

        // firstRowZero가 true라면 (first row이 0을 포함하고 있다면)
        // firstRow을 모두 zero로 설정해준다.
        if (firstRowZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        System.out.println("set zero to first column --------");
        Utils.print2Darray(matrix);
    }

    public static void main(String[] args) {
        SetMatrixZeroes t = new SetMatrixZeroes();

        //int[][] matrix = { { 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1 } , { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }};
        //int[][] matrix = { { 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1 } , { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1 }};
        //int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] matrix = { { 0, 1, 1, 0 }, { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 } };
        System.out.println("input ---------");
        Utils.print2Darray(matrix);
        t.sol1(matrix);
        /*System.out.println("---------");
        int[][] matrix2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        Utils.print2Darray(matrix2);
        System.out.println("---------");
        t.sol1(matrix2);
        Utils.print2Darray(matrix2);*/

    }
}

