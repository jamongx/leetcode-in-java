import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    /**
     * Union Find
     * Time: O(n)
     * Space: O(n)
     * @param nums
     * @return
     */
    public int sol1(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        for (int num : nums) {
            int count = 1;
            int down = num - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
                count++;
            }
            int up = num + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
                count++;
            }
            result = Math.max(result, count);
        }
        return result;
    }

    /**
     * Union Find
     * Time: O(n)
     * Space: O(n)
     * @param nums
     * @return
     */
    public int sol2(int[] nums) {

        int ans = 0;
        Set<Integer> seen = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        
        for (int num : nums) {

            // num is the start of a sequence
            // (num-1) 포함되어 있다면 (num-1) 일때 n (num-1)+1을 체크했을 것이므로 skip
            System.out.println("(num=" +num +"-1)=" +(num-1));
            if (seen.contains(num - 1)) {
                System.out.println("contain=true -> continue");
                continue;
            }

            int length = 1;
            while (seen.contains(++num)) {
                ++length;
                System.out.println("contain=(++num)=" +num +", len=" +length);
            }

            System.out.print("ans=" +ans);
            ans = Math.max(ans, length);
            System.out.println(", len=" +length +", max=" +ans);
        }

        return ans;
    }


    public static void main(String[] args) {
        LongestConsecutiveSequence t = new LongestConsecutiveSequence();

        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        //int[] nums = { 1, 3, 5, 2, 4, 9, 12, 15, 17, 20 };

        System.out.println(t.sol2(nums));
    }
}
