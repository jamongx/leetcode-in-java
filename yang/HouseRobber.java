
public class HouseRobber {

    public int sol1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        // dp[i]는 i까지 도달하여 가장 많이 훔친 돈을 저장하는 배열이다.
        int[] dp = new int[nums.length];

        // 초기 (0) 단계에서는 훔친 돈이 없으므로 0이다.
        dp[0] = nums[0];
        System.out.println("dp[0]=" + dp[0] +", nums[0]=" + nums[0]);

        // 연속해서 돈을 훔칠수 없으므로 nums[0]과 nums[1] 중에 큰 값을 dp[1]에 저장한다.
        dp[1] = Math.max(nums[0], nums[1]);
        System.out.println("dp[1]=" + dp[1] +" <- max(nums[0]=" + nums[0] +", nums[1]=" + nums[1] +")");

        for (int i = 2; i < nums.length; i++) {

            // i의 위치에서
            // nums[i]와 dp[i-2]를 더한 값과 dp[i-1]을 비교하여 큰값을 선택
            // 연속적으로 물건을 훔치면 안되므로 
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);

            System.out.println("dp[" +i +"]=" +dp[i] +" <- max(dp[" +(i-2) +"]=" +dp[i-2] +" + nums[" +i +"]=" +nums[i] +", dp[" +(i-1) +"]=" +dp[i-1] +")");
        }

        return dp[nums.length - 1];
    }


    public int sol2(int[] nums) {
        int dp_1 = 0; // dp[i - 1]
        int dp_2 = 0; // dp[i - 2]

        for (int num : nums) {
            int dp = Math.max(dp_1, dp_2 + num);
            dp_2 = dp_1; // dp[i-1]을 dp[i-2]로 복사한다.
            dp_1 = dp;   // dp를 dp[i-1]로 복사한다.
        }

        return dp_1;
    }

    public static void main(String[] args) {
        HouseRobber t = new HouseRobber();

        int[] nums = { 2, 7, 9, 3, 1 };

        System.out.println(t.sol1(nums));
        System.out.println(t.sol2(nums));
    }
}
