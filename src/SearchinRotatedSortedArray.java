public class SearchinRotatedSortedArray {

    /**
     * Recursive
     */
    public int sol1(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        if(left >= right) {
            return (nums[left] == target) ? left : -1;
        }

        int result = -1;

        //int m = (l + r) / 2;
        int mid = (left + right) >>> 1;

        if(nums[mid] == target) {
            result = mid;
        }
        else {
            // 반으로 나눠서 왼쪽에 검색
            result = binarySearch(nums, left, mid - 1, target);
            if(result < 0) {
                // 오른쪽에 검색
                result = binarySearch(nums, mid + 1, right, target);
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
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            //int m = (l + r) / 2;
            int mid = (left + right) >>> 1;

            // m값을 계산하는 알고리즘
            if (nums[mid] == target) {
                return mid;
            }

            // 만약 `nums[left] <= nums[mid]`이면, 왼쪽 부분은 정렬되어 있다.
            // "왼쪽 부분이 정렬되어 있다면, 타겟 값도 그 정렬된 부분에 있는지 확인하자.
			// 있다면 오른쪽은 볼 필요 없고, 없다면 왼쪽은 볼 필요 없다"
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            // 만약 `nums[mid] < nums[right]`이면, 오른쪽 부분은 정렬되어 있다.
            else {
                if (nums[mid] < target && target <= nums[right]) {
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
        SearchinRotatedSortedArray t = new SearchinRotatedSortedArray();

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println(t.sol2(nums, target));
    }
}
