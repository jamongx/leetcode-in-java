public class MissingNumber {


    public int sol3(int[] nums) {
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            ans ^= i ^ nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {

        MissingNumber t = new MissingNumber();

        // int[] nums = {9,6,4,2,3,5,7,0,1};
        int[] nums = {0,1};

        System.out.println(t.sol3(nums));
    }
}
