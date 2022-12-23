import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    // candidates를 인수로 받아서 계속 recursive하게 전달한다.
    // target 값
    public List<List<Integer>> sol1(int[] candidates, int target) {

        // 결과 리스트를 저장하는 리스트
        List<List<Integer>> result = new ArrayList<>();

        // 계산된 배열을 저장하는 리스트
        List<Integer> curr = new ArrayList<>();

        int curIndex = 0;
        backtracking(candidates, target, curIndex, result, curr);
        return result;
    }

    public void backtracking(int[] candidates, int target, int currIndex, List<List<Integer>> result, List<Integer> curr) {

        System.out.println(curr);

        // 종료조건 1
        if (target == 0) {
            List<Integer> one = new ArrayList<>(curr);
            result.add(one);
            System.out.println("add result=" +one);
        } 
        // 종료조건 2
        else if (target < 0 || currIndex == candidates.length) {

        } 
        else {
            // curr 리스트에 현재 candidate을 저장한다.
            curr.add(candidates[currIndex]);
            backtracking(candidates, target - candidates[currIndex], currIndex, result, curr);

            // curr 리스트에 마지막으로 넣었던 candidate을 
            curr.remove(curr.size() - 1);
            backtracking(candidates, target, currIndex + 1, result, curr);
        }
    }

    public static void main(String[] args) {
        CombinationSum t = new  CombinationSum();

        int[] candidates = { 2, 3, 5, 7 };
        int target = 7;
        List<List<Integer>> results = t.sol1(candidates, target);
    }
}
