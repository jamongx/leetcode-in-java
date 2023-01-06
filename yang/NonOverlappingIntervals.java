import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NonOverlappingIntervals {

    
    public int sol1(int[][] intervals) {
        if (intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int ans = 0;
        int currentEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] >= currentEnd) {
                currentEnd = intervals[i][1];
            }
            else {
                ++ans;
            }
        }
        
        return ans;
    }

    public int sol2(List<Interval> intvList) {
        if (intvList == null || intvList.size() <= 1) {
            return 0;
        }

        Collections.sort(intvList, Comparator.comparing((Interval itl) -> itl.start));

        int ans = 0;
        Interval t = intvList.get(0);

        for (int i = 1; i < intvList.size(); i++) {
            Interval c = intvList.get(i);
            if (c.start >= t.end) {
                t.end = c.end;
            } else {
                ++ans;
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
        NonOverlappingIntervals t = new NonOverlappingIntervals();


        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(1,2));
        //intervals.add(new Interval(2,3));
        //intervals.add(new Interval(3,4));
        //intervals.add(new Interval(1,3));

        System.out.println( t.sol2(intervals));
    }
}
