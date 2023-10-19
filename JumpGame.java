public class JumpGame {

    /**
     * Dynamic Programming
     * TC: O(n)
     * SC: O(1)
     */
    public boolean sol1(int[] nums) {

        // 길이가 1 이하이면 무조건 마지막 인덱스에 도달할 수 있으므로 true를 반환합니다.
        if (nums.length <= 1) {
            return true;
        }

        // 움직일 수 있는 최대 거리
        int reach = nums[0];

        for (int i = 0; i < nums.length; i++) {
            
            // reach <= i 이면, j(<i) + nums[j]가 i 보다 작다.
            // nums[i] == 0 즉, 움직일 수 있는 거리가 0이라면
            if (reach <= i && nums[i] == 0) {
                return false;
            }
            
            // 현재위치 i + num[i] 가 reach보다 크다면 update
            if (i + nums[i] > reach) {
                reach = i + nums[i];
            }
            
            System.out.println("i=" +i +", nums[" +i +"]=" +nums[i] +", max=" +reach);
            // max의 길이가 배열의 길이와 같거나 크다면 true
            if (reach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Greedy
     * TC: O(n)
     * SC: O(1)
     */
    public boolean sol2(int[] nums) {
        int maxReach = 0; // 가장 멀리 도달할 수 있는 인덱스를 저장할 변수
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) { // 현재 위치가 maxReach를 넘어가면 도달할 수 없다는 뜻이므로 false를 반환
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]); // 현재 위치에서 뛸 수 있는 거리를 더해 가장 멀리 도달할 수 있는 인덱스를 업데이트
        }
        return true; // 루프가 완료되면 마지막 인덱스에 도달할 수 있다는 뜻이므로 true를 반환
    }


    public static void main(String[] args) {
        JumpGame t = new JumpGame();

        int[] nums1 = { 2, 3, 1, 1, 4 };
        int[] nums2 = { 3, 2, 1, 0, 4 };

        // System.out.println(t.sol1(nums));
        System.out.println(t.sol2(nums1));
        System.out.println(t.sol2(nums2));
    }
}
