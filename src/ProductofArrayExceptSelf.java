
public class ProductofArrayExceptSelf {

    /**
     * TC: O(n)
     * SC: O(n)
     */
    public int[] sol1(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int[] prefix = new int[len];
        int[] suffix = new int[len];

        // 초기값으로 0을 넣으면 전부 0이 된다.
        prefix[0] = 1;
        for (int i = 1; i < len; i++) {
            // prefix[1] = prefix[0] (= 1) X nums[0]
            // prefix[2] = prefix[1] X nums[1]
            // prefix[3] = prefix[2] X nums[2]
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        suffix[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < len; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public int[] sol2(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        result[0] = 1;
        for (int i = 1; i < len; ++i) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        ProductofArrayExceptSelf t = new ProductofArrayExceptSelf();

        int[] nums = {1, 2, 3, 4};

        Utils.printArray(t.sol1(nums));
        Utils.printArray(t.sol2(nums));
    }

}
