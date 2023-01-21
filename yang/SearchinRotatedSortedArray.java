// binary search를 사용해 target의 index를 return
// In order to use binary search on the rotated sorted array, 
// We need to determine how to update the left and right pointers.
public class SearchinRotatedSortedArray {

    public int sol1(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        if(l >= r) {
            return (nums[l] == target) ? l : -1;
        }

        int result = -1;

        //int m = (l + r) / 2;
        int m = (l + r) >>> 1;

        if(nums[m] == target) {
            result = m;
        }
        else {
            result = binarySearch(nums, l, m - 1, target);
            if(result < 0) {
                result = binarySearch(nums, m + 1, r, target);
            }
        }

        return result;
    }


    /**
     * 
     * @param nums
     * @param target
     * @return the index of the target
     */
    public int sol2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {

            //int m = (l + r) / 2;
            int m = (l + r) >>> 1;

            // m값을 계산하는 알고리즘
            if (nums[m] == target) {
                return m;
            }

            // 정렬된 파트를 기준으로 검색한다.
            // left: nums[l..m] are sorted
            if (nums[l] <= nums[m]) {
                // target값의 위치로 r과 l값을 바꾼다.
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
            // right: nums[m..n - 1] are sorted
            else {
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        SearchinRotatedSortedArray t = new SearchinRotatedSortedArray();

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println(t.sol2(nums, target));
    }
}
