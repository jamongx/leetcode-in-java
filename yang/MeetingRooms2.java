import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;

public class MeetingRooms2 {

    public int sol1(List<Interval> list) {

        // Store end times of each room
        // 우선순위 큐 -> 미팅 끝나는 시간이 높은값 -> 마지막시간 -> top
        Queue<Integer> minHeap = new PriorityQueue<>();

        // merge sort
        Collections.sort(list, Comparator.comparing((Interval one) -> one.start));

        for (Interval curr : list) {

            if (!minHeap.isEmpty() && curr.start >= minHeap.peek()) {
                // No overlap, we can reuse the same room
                minHeap.poll();
            }
            minHeap.offer(curr.end);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        MeetingRooms2 t = new MeetingRooms2();

        List<Interval> list = new ArrayList<>();
        // list.add(new Interval(7,10));
        // list.add(new Interval(2,4));
        list.add(new Interval(0,30));
        list.add(new Interval(5,10));
        list.add(new Interval(15,20));

        System.out.println(t.sol1(list));
    }

}