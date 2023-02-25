import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    /**
     * Heap (Priority Queue)
     * TC: O(nlogk)
     * SC: O(n)
     * That heap is often used to reduce time complexity from nlogn to nlogk.
     */
    public List<Integer> sol1(int[] nums, int k) {

        // <number, frequency>
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        // create a min heap, entry.getKey, entry.getValue
        PriorityQueue<Map.Entry<Integer, Integer>> queue
         = new PriorityQueue<>(Comparator.comparing(e -> e.getValue()));

        // maintain a heap of size k.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll(); // minheap의 top을 pop한다.
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
     * Bucket Sort 
     * TC: O(n)
     * SC: O(n)
     */
    public List<Integer> sol2(int[] nums, int k) {

        // <number, frequency>
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        // get the max frequency
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        // initialize an array of ArrayList.
        // index is frequency, value is list of  numbers
        // 최대 빈도수 max 사이즈의 배열을 만든다.
        // max 보다 작은 빈도수는 0이 될수도 있다.
        @SuppressWarnings("unchecked")
        List<Integer>[] arr = new ArrayList[max + 1];
        for (int i = 1; i <= max; i++) {
            arr[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int number     = entry.getKey();
            int frequency  = entry.getValue();
            arr[frequency].add(number);
        }

        // Add most frequent numbers to result
        List<Integer> result = new ArrayList<>();
        for (int j = max; j >= 1; j--) {
            if (arr[j].size() > 0) {
                for (int a : arr[j]) {
                    result.add(a);
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
        System.out.println(t.sol2(nums, k));
    }

}
