import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> sol1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        
        int count = matrix.length * matrix[0].length;
        
        int row1 = 0;
        int row2 = matrix.length - 1;
        
        int col1 = 0;
        int col2 = matrix[0].length - 1;
        
        List<Integer> result = new ArrayList<>();
        while (result.size() < count) {

            // 북쪽
            for (int j = col1; j <= col2 && result.size() < count; j++) {
                result.add(matrix[row1][j]);
            }

            // 동쪽
            for (int i = row1 + 1; i <= row2 - 1 && result.size() < count; i++) {
                result.add(matrix[i][col2]);
            }

            // 남쪽
            for (int j = col2; j >= col1 && result.size() < count; j--) {
                result.add(matrix[row2][j]);
            }

            // 서쪽
            for (int i = row2 - 1; i >= row1 + 1 && result.size() < count; i--) {
                result.add(matrix[i][col1]);
            }

            row1++; // 시작 row 증가
            row2--; // 끝 row 감소
            col1++;
            col2--;
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix t = new SpiralMatrix();
        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9,10,11,12}};

        System.out.println(t.sol1(matrix));
    }
    
}
