import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    // Brute force: O(N^2)
    // depth first search -> 2^N
    public int sol1(int[] nums) {
        return 0;
    }

    // depth first search with cache -> N^2
    public int sol2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] max = new int[nums.length];
        Arrays.fill(max, 1);

        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // num[i]가 num[j]보다
                // index가 높은 수라서 값이 더 커야 한다.
                if (nums[i] > nums[j]) {
                    max[i] = Math.max(max[i], max[j] + 1);
                }
            }
            result = Math.max(max[i], result);
        }

        return result;
    }
    
    // O(nlogn)
    public int sol3(int[] nums) {

        int[] dp = new int[nums.length];
        int len = 0; // len of sequence

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);

            // if not found, binarySearch return -insertPosition-1
            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;

            // update len when insert position is at the end
            if (i == len) {
                len++;
            }
        }

        return len;
    }

    public int sol4(int[] nums) {
        List<Integer> piles = new ArrayList<>();
        for (int num : nums) {
            int index = Collections.binarySearch(piles, num, (a, b) -> a - b);
            if (index < 0)
                index = -(index+1);
                
            if(index == piles.size())
                piles.add(num);
            else
                piles.set(index, num);
        }
        return piles.size();
    }

    public static void main(String[] args) {

        LongestIncreasingSubsequence t = new LongestIncreasingSubsequence();

        //int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums = {0,1,0,3,2,3};
        System.out.println(t.sol2(nums));
    }
}
