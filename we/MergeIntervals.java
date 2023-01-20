package we;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
	public static void main(String args[]) {
		int input[][]= {{1,2},{6,7},{3,8}};
		System.out.println(Arrays.deepToString(new MergeIntervals().merge(input)));
	}
	
	public int[][] merge(int[][] intervals) {
		List<int[]> ans = new ArrayList<>();
		System.out.println(Arrays.deepToString(intervals));
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		System.out.println(Arrays.deepToString(intervals));
		
		for (int[] interval : intervals) {
			if (ans.isEmpty() || ans.get(ans.size() - 1)[1] < interval[0]) {
				ans.add(interval);
			} else {
				ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], interval[1]);
			}
		}
		return ans.toArray(new int[ans.size()][]);
	}

}
