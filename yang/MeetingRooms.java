import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

    public boolean sol1(List<Interval> intvList) {
        Collections.sort(intvList, Comparator.comparing((Interval itl) -> itl.start));

        for (int i = 0; i < intvList.size() - 1; i++) {
            if (intvList.get(i).end > intvList.get(i+1).start) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms t = new MeetingRooms();

        List<Interval> intervals = new ArrayList<>();
        //intervals.add(new Interval(7,10));
        //intervals.add(new Interval(2,4));
        intervals.add(new Interval(0,30));
        intervals.add(new Interval(5,10));
        intervals.add(new Interval(15,20));

        System.out.println(t.sol1(intervals));
    }
}
