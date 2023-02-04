public class FindMinimuminRotatedSortedArray {

    public int sol1(int[] nums) {

        // left, right index
        int l = 0;
        int r = nums.length - 1;

        // 원래 정렬된 배열이므로 l < r -> false가 되는 index가 최소값이다.
        while (l < r) {
            
            int m = (l + r) / 2;
            System.out.println("l=" +l +", r=" +r +", (l+r)/2=" +m +", nums[" +m +"]=" +nums[m] +", nums[" +r +"]=" +nums[r]);
            if (nums[m] < nums[r]) {
                r = m;
                System.out.println("l=" +l +", r(*)=" +r);
            }
            else {
                l = m + 1;
                System.out.println("l(*)=" +l +", r=" +r);
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArray sub = new FindMinimuminRotatedSortedArray();

        int[] nums = { 3,4,5,1,2 };

        System.out.println(sub.sol1(nums));
    }
    
}
