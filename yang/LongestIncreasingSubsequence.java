import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    /**
     * Approach 1: 2D DP
     * depth first search with cache -> N^2
     * @param nums
     * @return
     */
    public int sol1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] := Length of LIS ending at nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //int len = 1;
        for (int i = 1; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {
                    // num[i]가 num[j]보다 // index가 높은 수라서 값이 더 커야 한다.
                    // nums[j] < nums[i] -> true, 기존 dp[j]에 1 더해준다.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //len = Math.max(dp[i], len);
        }

        // instead of stream.max, return len;
        return Arrays.stream(dp).max().getAsInt(); // O(n)
    }


    // O(nlogn)
    public int sol2(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {

            int i = Collections.binarySearch(list, num, (a, b) -> a - b);
            // if not found, binarySearch return -insertPosition-1
            if (i < 0) {
                i = -(i+1);
            }
                
            // update len when insert position is at the end
            if(i == list.size()) {
                list.add(num);
            }
            else {
                // 순서를 바꿀수 없다. 리스트이 마지막보다 작으면 replace한다.
                list.set(i, num);
            }

            System.out.println("list=" +list);
        }
        return list.size();
    }

    public static void main(String[] args) {

        LongestIncreasingSubsequence t = new LongestIncreasingSubsequence();

        int[] nums = {10,9,2,5,3,7,101,18};
        //int[] nums = {0,1,0,3,2,3};

        System.out.println(t.sol1(nums));
        System.out.println(t.sol2(nums));
    }
}
