import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * Brute force
     * TC: O(n^2), for double loop
     * SC: O(1), no extra space allocated
     */
    public int[] sol1(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    /**
     * TC: O(n), for loop
     * SC: O(1), HashMap to push number of 'int[] nums'
     */
    public int[] sol2(int[] nums, int target) {

        // <target - nums[i], i>
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                return new int[] { map.get(nums[i]), i };
            }
            // target의 보수를 put 한다.
            map.put(target - nums[i], i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        TwoSum t = new TwoSum();

        int[] nums = { 1, 4, 7, 8 };
        int target = 11;

        Utils.printArray(t.sol2(nums, target));
    }
}