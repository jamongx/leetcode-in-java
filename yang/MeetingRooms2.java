import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms2 {

    public int sol1(List<Interval> intvList) {
        Collections.sort(intvList, Comparator.comparing((Interval itl) -> itl.start));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (Interval intv : intvList) {
            if (heap.isEmpty()) {
                count++;
                heap.offer(intv.end);
            } else {
                if (intv.start >= heap.peek()) {
                    heap.poll();
                } else {
                    count++;
                }
                heap.offer(intv.end);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MeetingRooms2 t = new MeetingRooms2();

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(7,10));
        intervals.add(new Interval(2,4));
        //intervals.add(new Interval(0,30));
        //intervals.add(new Interval(5,10));
        //intervals.add(new Interval(15,20));

        System.out.println(t.sol1(intervals));
    }

}