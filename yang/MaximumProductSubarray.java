public class MaximumProductSubarray {

    /*
     * edge case returns 0
     */
    public int sol1(int[] nums) {
        
        int result = nums[0];

        int max    = nums[0];
        int min    = nums[0];

        // negative 값이 곱해질 수 있으므로 min/max를 같이 계산해야 된다.
        // max와 min 값에 nums[i]를 각각 곱해서 temp max와 temp min을 만들다
        // 그리고 이 temp값들과 nums[i]를 곱해서 다시 min과 max를 구한다.
        for (int i = 1; i < nums.length; i++) {
            int tmpMax = max * nums[i];
            int tmpMin = min * nums[i];

            // nums[i], tmpMax, tmpMin에서 최대 값을 구한다.
            max    = Math.max(Math.max(nums[i], tmpMax), tmpMin);
            // nums[i], tmpMax, tmpMin에서 최소 값을 구한다.
            min    = Math.min(Math.min(nums[i], tmpMax), tmpMin);
            result = Math.max(max, result);
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumProductSubarray sub = new MaximumProductSubarray();

        int[] nums = { 2, 3, -2, 4 };

        System.out.println(sub.sol1(nums));
    }

}
