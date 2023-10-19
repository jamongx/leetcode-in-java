import java.util.List;
import java.util.ArrayList;

public class InsertInterval {

    /**
     * https://www.programcreek.com/2012/12/leetcode-insert-interval/ 
     * list의 interval들은 겹치지 않고 오른차순으로 정렬되어 있다.
     */
    public List<Interval> sol1(List<Interval> list, Interval ins) {

        List<Interval> result = new ArrayList<>();
        if (list.size() == 0) {
            result.add(ins);
            return result;
        }

        for (Interval curr : list) {
            // ins가 curr 다음에 있을때
            if (curr.end < ins.start) {
                result.add(curr);
            }
            // ins가 curr 의 앞에 있을때
            else if (ins.end < curr.start) {
                result.add(ins);
                ins = curr;
            }
            // ins가 curr 과 겹칠때
            else if (curr.start <= ins.end || curr.end >= ins.start) {
                ins = new Interval(Math.min(curr.start, ins.start),
                                   Math.max(ins.end, curr.end));
            }
        }

        result.add(ins);
        return result;
    }

    /**
     * https://www.programcreek.com/2012/12/leetcode-insert-interval/ 
     * sol1과 같은데 binary search가 들어간 부분이 다르다.
     */
    public List<Interval> sol2(List<Interval> list, Interval ins) {
        List<Interval> result = new ArrayList<>();
        if (list.size() == 0) {
            result.add(ins);
            return result;
        }

        // ins와 가까운 위치의 position을 찾는다.
        int pos = binarySearch(list, ins);

        // 0 부터 pos까지 sublist를 result에 add 한다.
        result.addAll(list.subList(0, pos));

        // pos 부터 끝까지 sublist에 ins를 merge 한다.
        for (int i = pos; i < list.size(); i++) {
            Interval curr = list.get(i);
            if (curr.end < ins.start) {
                result.add(curr);
            }
            else if (ins.end < curr.start) {
                result.add(ins);
                ins = curr;
            }
            else if (curr.end >= ins.start || curr.start <= ins.end) {
                ins = new Interval(Math.min(curr.start, ins.start),
                                   Math.max(ins.end, curr.end));
            }
        }
        result.add(ins);
        return result;
    }


    /**
     * java arrays binarysearch를 사용할 수 있다.
     * int position = Arrays.binarySearch(list.toArray(), ins);
     */
    public int binarySearch(List<Interval> list, Interval ins) {
        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (ins.start <= list.get(mid).start) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // pos -> high - 1
        return high == 0 ? 0 : high - 1;
    }

    public static void main(String[] args) {
        InsertInterval t = new InsertInterval();

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,2));
        list.add(new Interval(3,5));
        list.add(new Interval(6,7));
        list.add(new Interval(8,10));
        list.add(new Interval(12,16));

        Interval ins = new Interval(4, 8);

        System.out.println( t.sol1(list, ins));
        System.out.println( t.sol2(list, ins));
    }

}
