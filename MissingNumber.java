import java.util.Arrays;

public class MissingNumber {
    /**
     * 정렬한다음에 loop를 통해서 빠진 숫자를 찾을 수 있다.
     * @param nums
     * @return
     */

    /**
     * Math 
     * sum(n) = n X (n + 1) / 2
     */
    public int sol1(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int n = nums.length;
        return n * (n + 1) / 2 - sum;
    }

    /**
     * Bit manipulation
     * 배열은 0 부터 시작한다.
     * input 배열이 n 이면 원래 배열은 n + 1
     * ex) int [] nums = {0,1,2} -> {0,1,2,3}
     * 하지만 XOR을 사용하기 때문에 남는 숫자가 missing number가 된다.
     */
    public int sol2(int[] nums) {
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            System.out.print("(" +i +" ^ " +nums[i] +" = " +(i ^ nums[i]) +") ^ ans=" +ans);
            ans ^= i ^ nums[i];
            System.out.println(" -> ans=" +ans);
        }

        return ans;
    }

    /**
     * Binary Search
     */
    public int sol3(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length;

        while (l < r) {

            int m = (l + r) / 2;

            if (nums[m] > m) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {

        MissingNumber t = new MissingNumber();

        //int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        //int[] nums = {0, 1, 2};
        int[] nums = {1, 2, 3};

        System.out.println(t.sol2(nums));
    }
}
