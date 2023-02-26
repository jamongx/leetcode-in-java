public class SearchinRotatedSortedArray {

    /**
     * Recursive
     */
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
            // 반으로 나눠서 왼쪽에 검색
            result = binarySearch(nums, l, m - 1, target);
            if(result < 0) {
                // 오른쪽에 검색
                result = binarySearch(nums, m + 1, r, target);
            }
        }

        return result;
    }


    /**
     * Iterative
     * TC: O(log⁡n)
     * SC: O(1)
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
            // 1. left: nums[l..m] are sorted
            if (nums[l] <= nums[m]) {
                // target값이 nums[l]과 nums[m] 사이에 있다면
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
            // 2. right: nums[m..n - 1] are sorted
            else {
                // target값이 nums[m]과 nums[r] 사이에 있다면
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
