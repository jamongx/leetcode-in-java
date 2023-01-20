package we;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {
	public static void main(String args[]) {
		int input[][]= {{1,2},{6,9},{8,9}, {4,7}};
		System.out.println(new MeetingRoomsII().minMeetingRooms(input));
	}
		
	public int minMeetingRooms(int[][] intervals) {
		
	
		// Store end times of each room
		Queue<Integer> minHeap = new PriorityQueue<>();
		
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		
		for (int[] interval : intervals) {
			if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()) {
				minHeap.poll(); // No overlap, we can reuse the same room
			}
			minHeap.offer(interval[1]);
		}
		
		return minHeap.size();
	}

}
