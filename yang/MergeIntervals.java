import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public List<Interval> sol1(List<Interval> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }
        System.out.println("input=" +list);

        // merge sort -> O (n log n)
        // interval의 start 값으로 정렬 한다.
        Collections.sort(list, Comparator.comparing((Interval one) -> one.start));
        System.out.println("sort=" +list);

        List<Interval> result = new ArrayList<>();

        Interval temp = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            Interval curr = list.get(i);
            // Interval 두개가 겹치면 curr.end를 temp.end로 copy한다.
            if (curr.start <= temp.end) {
                temp.end = Math.max(temp.end, curr.end);
            } else {
                result.add(temp);
                temp = curr;
            }
        }

        result.add(temp);

        return result;
    }

    public static void main(String[] args) {
        MergeIntervals t = new MergeIntervals();

        // [[1,3], [2,6], [8,10], [15,18]]
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(15,18));
        intervals.add(new Interval(2,6));

        System.out.println(t.sol1(intervals));
    }


}
