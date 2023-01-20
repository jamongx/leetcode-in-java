import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    /**
     * https://www.programcreek.com/2014/05/leetcode-top-k-frequent-elements-java/
     * Approach1: Heap
     * Time complexity is O(n*log(k)).
     * Note that heap is often used to reduce time complexity from n*log(n) (see solution 3) to n*log(k).
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> sol1(int[] nums, int k) {

        // count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // create a min heap
        PriorityQueue<Map.Entry<Integer, Integer>> queue
         = new PriorityQueue<>(Comparator.comparing(e -> e.getValue()));

        // maintain a heap of size k.
        // k개를 return 해야 된다. 우선순위 queue에 k개만 유지한다.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        // get all elements from the heap
        List<Integer> result = new ArrayList<>();
        while (queue.size() > 0) {
            result.add(queue.poll().getKey());
        }

        // reverse the order
        Collections.reverse(result);
        return result;
    }


    /**
     * https://www.programcreek.com/2014/05/leetcode-top-k-frequent-elements-java/ 
     * Approch 2 - Bucket Sort 
     * Time: O(n)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> sol2(int[] nums, int k) {

        // count the frequency for each element
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // get the max frequency
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        // initialize an array of ArrayList. index is frequency, value is list of  numbers
        // https://sayit.tistory.com/entry/unchecked-conversion
        // ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[max + 1];
        @SuppressWarnings("unchecked")
        List<Integer>[] arr = new ArrayList[max + 1];
        for (int i = 1; i <= max; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count  = entry.getValue();
            int number = entry.getKey();
            arr[count].add(number);
        }

        List<Integer> result = new ArrayList<Integer>();
        // add most frequent numbers to result
        for (int j = max; j >= 1; j--) {
            if (arr[j].size() > 0) {
                for (int a : arr[j]) {
                    result.add(a);
                    // if size==k, stop
                    if (result.size() == k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        TopKFrequentElements t = new TopKFrequentElements();

        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        System.out.println(t.sol1(nums, k));
    }

}
