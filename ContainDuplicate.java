import java.util.Arrays;
import java.util.HashSet;

public class ContainDuplicate {
   
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
     * Arrays.sort-> O(nlog(n)), 그 다음 loop는 O(n)
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
     * loop -> O(n) 
     */
    public boolean sol3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,4,3,2,4,2};

        ContainDuplicate t = new ContainDuplicate();
        System.out.println(t.sol3(nums));
    }


}
