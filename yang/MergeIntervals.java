import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {

    public List<Interval> mergeIntervals(Interval[] intervals) {
        int n = intervals.length;
        List<Interval> res = new ArrayList<>();

        boolean vis[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }

            vis[i] = true;
            int minS = intervals[i].start;
            int maxE = intervals[i].end;

            while (true) {
                int c = 0;

                for (int j = 0; j < n; j++) {
                    if (!vis[j] && isOverlap(minS, maxE, intervals[j])) {
                        vis[j] = true;
                        minS = Math.min(minS, intervals[j].start);
                        maxE = Math.max(maxE, intervals[j].end);
                        c++;
                    }
                }

                if (c == 0) {
                    break;
                }
            }

            new Interval(1,2);

            res.add(new Interval(minS, maxE));
        }

        Collections.sort(res, new Comparator<Interval>() {

            public int compare(Interval a, Interval b) {
                if (a.start == b.start) {
                    return a.end - b.end;
                }

                return a.start - b.start;
            }

        });

        return res;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


    public boolean isOverlap(int minS, int maxE, Interval i) {
        if (minS > i.end || maxE < i.start) {
            return false;
        }

        return true;
    }
}
