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
        // (중요) End 값으로 정렬 한다.
        Collections.sort(list, Comparator.comparing((Interval one) -> one.end));
        System.out.println("sort=" +list);

        int result = 0;
        Interval prev = list.get(0);
        System.out.println("temp[0]=" +prev);

        // 겹치지 않는 interval 들을 붙여서 하나의 interval을 만든다.
        // 겹치는 것은 count를 해서 return한다.
        for (int i = 1; i < list.size(); i++) {
            Interval curr = list.get(i);
            if (prev.end <= curr.start) {
                prev.end = curr.end;
            } else {
                System.out.println("remove=" +curr);
                result++;
            }
            System.out.println("temp[" +i +"]=" +prev);
        }
        return result;
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
