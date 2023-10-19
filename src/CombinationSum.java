import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> sol1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }


    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> current,
                           List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            // i를 다시 전달하는 이유는 같은 숫자를 여러 번 사용할 수 있기 때문
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }


    public static void main(String[] args) {
        CombinationSum t = new  CombinationSum();

        int[] candidates = { 2, 3, 5, 7 };
        int target = 7;

        List<List<Integer>> results = t.sol1(candidates, target);
        System.out.println(results);
    }
}
