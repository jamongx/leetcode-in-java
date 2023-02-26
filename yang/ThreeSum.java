import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> sol1(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new LinkedList<>();

        // 먼저 nums를 정렬한다.
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // num[i]와 num[i-1]이 같으면 중복이므로 skip한다.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Choose nums[i] as the first num in the triplet,
            // and search the remaining nums in [i + 1, n - 1]
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // avoid duplicate solutions
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
                else if (sum > 0) {
                    right--; // right--은 sum을 감소시킨다.
                }
                else {
                    left++; // left++은 sum을 증가시킨다.
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        ThreeSum t = new ThreeSum();
        System.out.println(t.sol1(nums));
    }

}