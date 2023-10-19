import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> sol1(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        // 먼저 nums를 정렬한다.
        Arrays.sort(nums);

        // nums[i]를 기준으로 nums[i+1]를 left, nums[length-1] right로 정하고
        // left, right로 two pointers 알고리즘을 수행한다.
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
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
                    // right--은 sum을 감소시킨다. 정렬을해서 오른쪽이 크다.
                    right--;
                }
                else {
                    // left++은 sum을 증가시킨다. 정렬을해서 왼쪽이 작다.
                    left++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        ThreeSum t = new ThreeSum();
        System.out.println(t.sol1(nums));
    }
}