public class HouseRobber2 {

    public int sol1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob1(nums, 0, nums.length - 2),
                        rob1(nums, 1, nums.length - 1));
    }

    public int rob1(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }

        int[] dp = new int[nums.length];
        dp[l] = nums[l];
        dp[l + 1] = Math.max(nums[l + 1], dp[l]);

        for (int i = l + 2; i <= r; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[r];
    }


    public int sol2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob2(nums, 0, nums.length - 2),
                        rob2(nums, 1, nums.length - 1));
    }

    private int rob2(int[] nums, int l, int r) {
        int dp_1 = 0; // dp[i - 1]
        int dp_2 = 0; // dp[i - 2]

        for (int i = l; i <= r; i++) {
            int dp = Math.max(dp_1, dp_2 + nums[i]);
            dp_2  = dp_1;
            dp_1  = dp;
        }
        return dp_1;
    }


    public static void main(String[] args) {
        HouseRobber2 t = new HouseRobber2();

        int[] nums = { 2, 7, 9, 3, 1 };

        System.out.println( t.sol1(nums));
    }
   
}
