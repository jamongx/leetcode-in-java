public class JumpGame {

    public boolean sol1(int[] nums) {

        // 길이가 1 이하이면 무조건 마지막 인덱스에 도달할 수 있으므로 true를 반환합니다.
        if (nums.length <= 1) {
            return true;
        }

        // max 움직일 수 있는 최대 거리이다.
        // max 값은 index 의 value 값이다.
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {

            System.out.println("i=" +i +", nums[" +i +"]=" +nums[i] +", max=" +max);

            // max <= i 앞으로 갈 거리가 많이 남았는데 
            // nums[i] == 0 즉, 움직일 수 있는 거리가 0이라면
            if (max <= i && nums[i] == 0) {
                return false;
            }

            // 현재위치 i와 이동할수 있는 거리 num[i]가 max보다 크다면
            // max값을 update 한다.
            if (i + nums[i] > max) {
                max = i + nums[i];
            }
            
            // max의 길이가 배열의 길이와 같거나 크다면 true를 return 한다.
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    // sol1 보다 간단한 알고리즘
    public boolean sol2(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max; i++) {
            System.out.println("i=" + i + ", nums[" + i + "]=" + nums[i] + ", max=" + max);
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        JumpGame t = new JumpGame();

        int[] nums = { 2, 3, 1, 1, 4 };
        // int[] nums = { 3, 2, 1, 0, 4 };

        // System.out.println(t.sol1(nums));
        System.out.println(t.sol1(nums));
    }
}
