// binary search를 사용해 target의 index를 return
// In order to use binary search on the rotated sorted array, 
// We need to determine how to update the left and right pointers.
public class SearchinRotatedSortedArray {

    public int sol1(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if(start >= end) {
            return (nums[start] == target) ? start : -1;
        }

        int ret = -1;
        int mid = (start + end) >>> 1;
        if(nums[mid] == target) {
            ret = mid;
        }
        else {
            ret = binarySearch(nums, start, mid - 1, target);
            if(ret < 0)
                ret = binarySearch(nums, mid + 1, end, target);
        }

        return ret;
    }    

    public int sol2(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // int mid = left + (right-left)/2;
            int mid = (left + right) >>> 1;

            if (target == nums[mid]) {
                return mid;
            }

            // left sorted portion
            if (nums[left] <= nums[mid]) {
                // if(target > nums[mid] || target < nums[left]) {
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            // right sorted portion
            else {
                // if(target < nums[mid] || target > nums[right]) {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        SearchinRotatedSortedArray t = new SearchinRotatedSortedArray();
        //System.out.println(t.sol1(nums, target));
        System.out.println(t.sol2(nums, target));
    }
}
