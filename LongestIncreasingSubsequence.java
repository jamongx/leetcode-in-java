import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    /**
     * 2D DP
     * depth first search with cache
     * TC: O(N^2)
     * SC: O(N)
     */
    public int sol1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] := Length of LIS ending at nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int right = 1; right < nums.length; right++) {

            for (int left = 0; left < right; left++) {

                if (nums[left] < nums[right]) {
                    dp[right] = Math.max(dp[right], dp[left] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[right]);
        }

        return maxLength;
    }


    public int sol2(int[] nums) {

        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            int i = 0;
            int j = size;
            while (i != j) {
                int mid = (i + j) / 2;
                if (tails[mid] < num) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = num;
            if (i == size) {
                ++size;
            }
        }
        return size;
    }

    /**
     * TC: O(nlogn)
     */
    public int sol3(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {

            int i = Collections.binarySearch(list, num, (a, b) -> a - b);
            // if not found, binarySearch return -insertPosition-1
            System.out.println("i=" +i);
            if (i < 0) {
                i = -(i+1);
            }
                
            // update len when insert position is at the end
            // 리스트의 모든 값 보다 큰 숫자면 마지막에 추가한다.
            if(i == list.size()) {
                list.add(num);
            }
            else {
                // 순서를 바꿀 필요가 없다. 리스트이 마지막보다 작으면 replace한다.
                // list=[2, 3, 7, 101] <- 18
                // i=(-4)
                // list=[2, 3, 7, 18] <- replace
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
        System.out.println(t.sol3(nums));
    }
}
