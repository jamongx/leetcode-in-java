public class FindMinimuminRotatedSortedArray {

    public int sol1(int[] A) {
        int L = 0;
        int R = A.length - 1;

        while (L < R && A[L] >= A[R])
        {
            int M = (L + R) / 2;
            if (A[M] > A[R])
            {
                L = M + 1;
            }
            else
            {
                R = M;
            }
        }
        return A[L];
    }

    public static void main(String[] args) {
        int[] nums = { 3,4,5,1,2 };

        FindMinimuminRotatedSortedArray sub = new FindMinimuminRotatedSortedArray();
        System.out.println(sub.sol1(nums));
    }


    
}
