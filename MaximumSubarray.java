public class MaximumSubarray {

    public int sol1(int[] A) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    /*
     * The changing condition for dynamic programming is
     * We should ignore the sum of the previous n-1 elements
     * if nth element is greater than the sum.
     */
    public int sol2(int[] nums) {

        int max = nums[0];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
            max = Math.max(max, sum[i]);
        }
        return max;
    }

    /*
     * O(n) runtime, O(1) space – Dynamic programming
     */
    public int sol3(int[] nums) {

        int maxSub = nums[0];
        int curSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxSub = Math.max(curSum, maxSub);
        }

        return maxSub;
    }

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        MaximumSubarray sub = new MaximumSubarray();
        System.out.println(sub.sol3(nums));
    }

}
