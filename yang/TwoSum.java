import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * time complexity : for double loop -> O(n^2)
     * space complexity : no extra space allocated -> O(1)
     */
    public int[] sol1(int[] nums, int target) {

        for(int i = 0; i < nums.length - 1; i++) {

            for(int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] sol2(int[] nums, int target) {
        // <given number, its index>
        Map<Integer, Integer> map = new HashMap<> ();

        // 일단 다 넣는다.
        for(int i = 0; i < nums.length; i++) {
             map.put(nums[i], i);
        }

        // target의 보수를 찾는다.
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)
             && map.get (complement) != i) { // return index와 loop index가 달라야 한다.
                return new int[] {i, map.get(complement)};
            }
        }
        return null;
    }

    /**
     * time complexity : for loop -> O(n)
     * space complexity : HashMap to push number of 'int[] nums' -> O(n)
     */
    public int[] sol3(int[] nums, int target) {
        // <given number, its index>
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {

            // 현재 값으로 get해서 있으면
            // loop의 index와
            // get 한값 loop값의 보수의 index를 return 한다.
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 7, 8};
        int target = 11;

        TwoSum t = new TwoSum();
        System.out.println(Arrays.toString(t.sol2(nums, target)));
    }
}