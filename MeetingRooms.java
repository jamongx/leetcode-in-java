import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

    public boolean sol1(List<Interval> list) {

        // merge sort
        Collections.sort(list, Comparator.comparing((Interval one) -> one.start));

        for (int i = 0; i < list.size() - 1; i++) {

            if (list.get(i).end > list.get(i+1).start) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms t = new MeetingRooms();

        List<Interval> list = new ArrayList<>();
        //intervals.add(new Interval(7,10));
        //intervals.add(new Interval(2,4));
        list.add(new Interval(0,30));
        list.add(new Interval(5,10));
        list.add(new Interval(15,20));

        System.out.println(t.sol1(list));
    }
}
