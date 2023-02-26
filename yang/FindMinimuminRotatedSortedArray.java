public class FindMinimuminRotatedSortedArray {

    public int sol1(int[] nums) {

        // left, right index
        int l = 0;
        int r = nums.length - 1;

        // 원래 정렬된 배열이므로
        // (l < r) -> false가 되면 nums[l]이 최소값이다.
        while (l < r) {
            
            int m = (l + r) / 2;
            System.out.println("l=" +l +", r=" +r +", (l+r)/2=" +m +", nums[" +m +"]=" +nums[m] +", nums[" +r +"]=" +nums[r]);
            // 최소값을 찾아야 하기 때문에 nums[m]와 num[r]를 비교해서
            // left 또는 right를 mid로 이동한다.
            if (nums[m] < nums[r]) {
                // right -> mid로 이동
                r = m;
                System.out.println("l=" +l +", r(*)=" +r);
            }
            else {
                // left - mid+1로 이동
                l = m + 1;
                System.out.println("l(*)=" +l +", r=" +r);
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray sub = new FindMinimuminRotatedSortedArray();

        ///int[] nums = { 3, 4, 5, 1, 2 };
        int[] nums = { 12, 13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        System.out.println(sub.sol1(nums));
    }
    
}
