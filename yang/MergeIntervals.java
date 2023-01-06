import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public List<Interval> sol1(List<Interval> intvList) {
        if (intvList == null || intvList.size() <= 1) {
            return intvList;
        }

        Collections.sort(intvList, Comparator.comparing((Interval itl) -> itl.start));

        List<Interval> ans = new ArrayList<>();

        Interval t = intvList.get(0);

        for (int i = 1; i < intvList.size(); i++) {
            Interval c = intvList.get(i);
            if (c.start <= t.end) {
                t.end = Math.max(t.end, c.end);
            } else {
                ans.add(t);
                t = c;
            }
        }

        ans.add(t);

        return ans;
    }

    public static void main(String[] args) {
        InsertInterval t = new InsertInterval();

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));

        Interval intvNew = new Interval(4, 8);

        System.out.println( t.sol1(intervals, intvNew));
    }


}
