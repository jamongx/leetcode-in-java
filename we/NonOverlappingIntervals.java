package we;

import java.util.Arrays;

public class NonOverlappingIntervals {
	public static void main(String args[]) {
		int input[][]= {{1,3},{2,4},{3,8},{5,6},{7,8}};
		System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(input));
	}
	
	public int eraseOverlapIntervals(int[][] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		System.out.println(Arrays.deepToString(intervals));
		Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
		System.out.println(Arrays.deepToString(intervals));
		int ans = 0;
		int currentEnd = intervals[0][1];
		
		for (int i = 1; i < intervals.length; ++i) {
			System.out.println(intervals[i][0] + " "+ currentEnd);
			if (intervals[i][0] >= currentEnd) {
				currentEnd = intervals[i][1];
			} else {
				System.out.println("A");
				++ans;
			}
		}
		return ans;
	}

}
