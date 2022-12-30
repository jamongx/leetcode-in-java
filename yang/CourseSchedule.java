import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseSchedule {

    /**
     * DFS
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean sol1(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        /* HashMap: 어떤 선수에 이어지는 과목의 리스트를 저장한다.
         * key:   a[1]: 선수
         * value: a[0]: 과목 */
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] a : prerequisites) {

            if (map.containsKey(a[1])) {
                map.get(a[1]).add(a[0]);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(a[0]);
                map.put(a[1], list);
            }
        }

        // track visited courses
        int[] visit = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(map, visit, i)) {
                return false;
            }
        }

        return true;
    }

    // i -> 과목 번호
    private boolean canFinishDFS(Map<Integer, List<Integer>> map, int[] visit, int i) {
        // 이미 방문해서 cyclic이 확인되었다.
        if (visit[i] == -1) { return false; }
        // 이미 방문해서 acyclic이 확인되었다.
        if (visit[i] ==  1) { return true;  }

        visit[i] = -1;

        // 선수가 map에 포함되어 있다면
        if (map.containsKey(i)) {

            // 선수에 연결된 과목 리스트를 가져온다.
            for (int j : map.get(i)) {

                if (!canFinishDFS(map, visit, j)) {
                    return false;
                }
            }
        }

        visit[i] = 1;

        return true;
    }


    /**
     * BFS
     * @param numCourses 과목의 개수
     * @param prerequisites[과목][선수] -> new prerequisites[5][1]
     * 1. prerequisites[0][0]=1 -> 첫번째 과목 번호 1
     * 2. prerequisites[0][1]=2 -> 첫번째 과목의 선수 2
     * @return return [true] if you can finish all courses
     */
    public boolean sol2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        int len = prerequisites.length;

        // 어떤 과목이 가지고 있는 선수의 개수
        int[] preCounter = new int[numCourses];
        for (int i = 0; i < len; i++) {
            preCounter[prerequisites[i][0]]++;
        }

        // 선수 과목이 없는 과목을 queue에 넣는다.
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (preCounter[i] == 0) {
                queue.add(i);
            }
        }

        // 계산하기 전에 선수 과목이 없는 것의 개수
        int numNoPre = queue.size();

        while (!queue.isEmpty()) {

            // 선수가 없는 과목을 하나 뺀다.
            int top = queue.remove();
            for (int i = 0; i < len; i++) {

                // 선수가 없는 것 (top)과 어떤 과목의 선수와 일치하면
                // preCounter를 하나 뺀다.
                // 그래서 선수 과목이 없는 상태가 되면 queue에 넣는다.
                if (prerequisites[i][1] == top) {

                    preCounter[prerequisites[i][0]]--;
                    if (preCounter[prerequisites[i][0]] == 0) {
                        numNoPre++; // 선수 과목이 없는 과목 개수를 1 늘려준다.
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return (numNoPre == numCourses);
    }

    public static void main(String[] args) {
        CourseSchedule t = new CourseSchedule();

        int numCourses = 2;
        int[][] prerequisites = { {1, 0}, {0, 1} };

        // int numCourses = 5; // 0 -> 4
        // int[][] prerequisites = { {1, 0}, {2, 1}, {3, 2}, {4, 3} };

        System.out.println(t.sol2(numCourses, prerequisites));
    }


}
