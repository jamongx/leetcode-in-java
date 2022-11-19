public class MaximumProductSubarray {

    /*
     * edge case: 0
     */
    public int sol1(int[] nums) {
        assert nums.length > 0;

        int max    = nums[0];
        int min    = nums[0];
        int maxAns = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tmpMax = max * nums[i];
            int tmpMin = min * nums[i];
            max    = Math.max(Math.max(nums[i], tmpMax), tmpMin);
            min    = Math.min(Math.min(nums[i], tmpMax), tmpMin);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        int[] nums = { 2,3,-2,4 };

        MaximumProductSubarray sub = new MaximumProductSubarray();
        System.out.println(sub.sol1(nums));
    }

}
