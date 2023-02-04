import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NonOverlappingIntervals {

    public int sol1(List<Interval> list) {
        if (list == null || list.size() <= 1) {
            return 0;
        }

        // merge sort: o(nlogn)
        // end 값으로 정렬을 해야 된다.
        Collections.sort(list, Comparator.comparing((Interval one) -> one.end));
        System.out.println("sort=" +list);

        int count = 0;
        Interval temp = list.get(0);
        System.out.println("temp[0]=" +temp);

        // 겹치지 않는 것을 이어서 가장 긴 것을 만든다로 문제를 전환한다.
        // 겹치는 것은 count를 해서 return한다.
        for (int i = 1; i < list.size(); i++) {
            Interval curr = list.get(i);
            if (curr.start >= temp.end) {
                temp.end = curr.end;
            } else {
                System.out.println("remove=" +curr);
                count++;
            }
            System.out.println("temp[" +i +"]=" +temp);
        }
        return count;
    }
    
    public static void main(String[] args) {
        NonOverlappingIntervals t = new NonOverlappingIntervals();

        /*List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,2)); // temp (1,2)
        list.add(new Interval(2,3)); // temp (1,3)
        list.add(new Interval(3,4)); // temp (1,4)
        list.add(new Interval(1,3)); /// -> remove*/

        // {{1,3},{2,4},{3,8},{5,6},{7,8}}
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,3));
        list.add(new Interval(2,4));
        list.add(new Interval(3,8));
        list.add(new Interval(5,6));
        list.add(new Interval(7,8));
        System.out.println(t.sol1(list));
    }
}
