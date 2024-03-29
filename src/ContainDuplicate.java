import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class ContainDuplicate {
   
    /**
     * brute force
     * TC: O(n^2)
     */
    public boolean sol1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * TC: 
     * Arrays.sort (merge sort) -> O(nlogn)
     * loop -> O(n)
     */
    public boolean sol2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * TC:
     * signle loop -> O(n) 
     */
    public boolean sol3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainDuplicate t = new ContainDuplicate();

        int[] nums = {1,1,1,3,3,4,3,2,4,2};

        System.out.println(t.sol3(nums));
    }
}
