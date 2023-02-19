import java.util.ArrayList;
import java.util.Arrays;
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

            if (!minHeap.isEmpty()) {
                int prev_end = minHeap.peek();
                if(curr.start >= prev_end) {
                    // 겹치지 않으므로 이전 meeting room을 계속해서 사용할 수 있다.
                    minHeap.poll();
                }
            }
            minHeap.offer(curr.end);
        }

        // minHeap에 meeting이 남아 있다면 meeting room이 그만큼 더 필요한 것이다.
        return minHeap.size();
    }

    public int sol2(int[][] intervals) {
        int n       = intervals.length;
        int[] start = new int[n];
        int[] end   = new int[n];
        
        for (int i = 0; i < n; ++i) {
            start[i] = intervals[i][0];
            end[i]   = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        // j points to ends
        int room = 0;
        for (int i = 0, j = 0; i < n; i++) {

            System.out.print("start[i:" +i +"]=" +start[i] +", end[j:" +j +"]=" +end[j]);
            // 어떤 start time이 end time보다 작으므로 방이 하나더 필요하다
            if (start[i] < end[j]) {
                room++;
                System.out.println(", room=" +room);
            }
            else {
                j++;
                System.out.println(", j=" +j);
            }
        }

        return room;
    }

    public static void main(String[] args) {
        MeetingRooms2 t = new MeetingRooms2();

        List<Interval> list1 = new ArrayList<>();
        // list.add(new Interval(7,10));
        // list.add(new Interval(2,4));
        list1.add(new Interval(0,30));
        list1.add(new Interval(5,10));
        list1.add(new Interval(15,20));

        System.out.println(t.sol1(list1));

        int [][] list2 = {{1,3},{4,5},{5,7},{9,11},{2,8},{6,10}};

        System.out.println(t.sol2(list2));
    }

}