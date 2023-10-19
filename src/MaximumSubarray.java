public class MaximumSubarray {

    /**
     * Dynamic programming
     * TC: O(n)
     * SC: O(n)
     * The changing condition for dynamic programming is
     * We should ignore the sum of the previous n-1 elements
     * if nth element is greater than the sum.
     */
    public int sol1(int[] nums) {

        int max = nums[0];

        int[] sum = new int[nums.length];
        // sum은 i까지 sub-array의 가장 큰 sum 값
        // sum[i-1]의 값을 사용하여 sum[i]값을 계산한다.
        // dynamic programming
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
            max    = Math.max(max, sum[i]);
        }
        return max;
    }

    /**
     * Dynamic programming
     * TC: O(n)
     * SC: O(1)
     */
    public int sol2(int[] nums) {

        int max = nums[0]; // max, the sum of sequence numbers
        int sum = nums[0]; // big value between sum + num[i], num[i]

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            // bigger value between the past sum (max) and current sum (sum)
            max = Math.max(sum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        MaximumSubarray sub = new MaximumSubarray();

        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        System.out.println(sub.sol2(nums));
    }

}
