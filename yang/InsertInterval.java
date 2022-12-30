import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                result.add(newInterval);
                newInterval = interval;
            } else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
                newInterval = new Interval(Math.min(interval.start, newInterval.start),
                        Math.max(newInterval.end, interval.end));
            }
        }
        result.add(newInterval);
        return result;
    }

    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int p = helper(intervals, newInterval);
        result.addAll(intervals.subList(0, p));
        for (int i = p; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                result.add(newInterval);
                newInterval = interval;
            } else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
                newInterval = new Interval(Math.min(interval.start, newInterval.start),
                        Math.max(newInterval.end, interval.end));
            }
        }
        result.add(newInterval);
        return result;
    }

    public int helper(List<Interval> intervals, Interval newInterval) {
        int low = 0;
        int high = intervals.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (newInterval.start <= intervals.get(mid).start) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high == 0 ? 0 : high - 1;
    }
}
