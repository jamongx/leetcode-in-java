import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

    /**
     * 
     * @param intvList element 들은 겹치지 않고 오른차순으로 정렬되어 있다.
     * @param intvNew
     * @return
     */
    public List<Interval> sol1(List<Interval> intvList, Interval intvNew) {
        List<Interval> ans = new ArrayList<>();
        if (intvList.size() == 0) {
            ans.add(intvNew);
            return ans;
        }

        for (Interval intvOne : intvList) {
            // 새로운 intv가 intvOne의 다음에 있을때
            if (intvOne.end < intvNew.start) {
                ans.add(intvOne);
            } else
            // 새로운 intv가 intvOne와 앞에 있을때
            // 새로운 intv를 ans에 add하고 intvOne을 intvNew로 replace한다.
            if (intvOne.start > intvNew.end) {
                ans.add(intvNew);
                intvNew = intvOne;
            } else
            // intvOne과 intvNew가 겹칠때
            if (intvOne.start <= intvNew.end || intvOne.end >= intvNew.start) {
                intvNew = new Interval(Math.min(intvOne.start, intvNew.start),
                                       Math.max(intvNew.end, intvOne.end));
            }
        }
        ans.add(intvNew);
        return ans;
    }


    public List<Interval> sol2(List<Interval> intvList, Interval intvNew) {
        List<Interval> ans = new ArrayList<>();
        if (intvList.size() == 0) {
            ans.add(intvNew);
            return ans;
        }

        // intvNew와 가까운 위치의 position을 찾는다.
        int position = helper(intvList, intvNew);

        ans.addAll(intvList.subList(0, position));
        for (int i = position; i < intvList.size(); i++) {
            Interval intvOne = intvList.get(i);
            if (intvOne.end < intvNew.start) {
                ans.add(intvOne);
            } else
            if (intvOne.start > intvNew.end) {
                ans.add(intvNew);
                intvNew = intvOne;
            } else
            if (intvOne.end >= intvNew.start || intvOne.start <= intvNew.end) {
                intvNew = new Interval(Math.min(intvOne.start, intvNew.start),
                                       Math.max(intvNew.end, intvOne.end));
            }
        }
        ans.add(intvNew);
        return ans;
    }

    public int helper(List<Interval> intvList, Interval intvNew) {
        int low = 0;
        int high = intvList.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (intvNew.start <= intvList.get(mid).start) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high == 0 ? 0 : high - 1;
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
