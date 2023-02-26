import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> sol1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        dfs(0, candidates, target, new ArrayList<>(), result);
        return result;
    }


    private void dfs(int s, int[] candidates, int target,
                             List<Integer> path, //result에 add할 path
                             List<List<Integer>> result) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = s; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(i, candidates, target - candidates[i], path, result);
            path.remove(path.size() - 1);
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
