import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianfromDataStream {

    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;

    /**
     * initialize your data structure here.
     */
    public FindMedianfromDataStream() {
        // 제일 작은 값이 root
        minHeap = new PriorityQueue<>();
        // 제일 높은 값이 root
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * <Concept>
     * maxHeap -> lower half -> 6, 7, 8, 9, (10)
     * minHeap -> higher half -> 15, 14, 13, 12, (11)
     * median -> (10 + 11) / 2 -> 10.5
     * 
     * <Algorithm>
     * 1. 새로운 num을 minHeap에 offer한다 -> 그러면 minHeap의 root 값이 제일 작은 값이다.
     * 2. 그리고 minHeap의 제일 작은 값을 poll 한다.
     * 3. 그 값을 다시 maxHeap에 넣는다.
     * 
     * <Core>
     * minHeap과 maxHeap의 size 차이가 +1을 넘지 않도록 밸런스를 맞추는 것이 핵심
     * 1. empty 상태에서 추가되면 minHeap의 모든 값은 maxHeap 보다 작다
     * 2. maxHeap이 항상크게, maxHeap과 minHeap의 차이가 1을 넘지 않도록
     */
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            // num > maxHeap.peek 이면
            // maxHeap(Lower Half)이 아니라 minHeap(Higher Half)로 넣는다
            minHeap.offer(num);
        }

        // minHeap과 maxHeap의 발란스를 맞춘다.
        // 1. |maxHeap| >= |minHeap|
        // 2. |maxHeap| - |minHeap| <= 1
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        else if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
    }


    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else {
            // maxHeap의 갯수가 더 많도록 addNum에 코드를 넣어 두었으므로
            return (double) maxHeap.peek();
        }
    }


    public static void main(String[] args) {
        FindMedianfromDataStream t = new FindMedianfromDataStream();

        t.addNum(1); // arr = [1]
        System.out.println(t.findMedian());
        t.addNum(2); // arr = [1, 2]
        System.out.println(t.findMedian());
        t.addNum(3); // arr[1, 2, 3]
        System.out.println(t.findMedian());

    }

}
