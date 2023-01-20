import java.util.Arrays;

public class ProductofArrayExceptSelf {

    /*
     * TC: O(n) without using the division operation
     */
    public int[] sol1(int[] nums) {
        int len = nums.length;

        int[] answer = new int[len];
        
        int left = 1;
        for(int i = 0; i < len; i++) {
            answer[i] = left;
            left = left * nums[i];
            //System.out.println(left);
        }

        //for(int i = 0; i < len; i++) {
        //    System.out.println(answer[i]);
        //}
        
        int right = 1;
        for(int i = len - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right = right * nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        ProductofArrayExceptSelf t = new ProductofArrayExceptSelf();
        System.out.println(Arrays.toString(t.sol1(nums)));
    }

}
