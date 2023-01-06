import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CourseSchedule {

    /**
     * DFS
     * @param numCourses
     * @param prerequisites
     * [1, 2], [1, 3], [2, 4] ...
     * 1. prerequisites[n][0]=1 -> 선수과목
     * 2. prerequisites[n][1]=2 -> 선택과목
     * @return
     */
    public boolean sol1(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        /* Build Hash map: 어떤 선수과목에 이어지는 선택과목의 연결관계 리스트를 저장
         * - Key  : a[n][1] 선수과목
         * - Value: a[n][0] 선택과목 */
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

        // 각 과목(visit)중에서 cyclic이 있는지 확인한다.
        int[] visit = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(map, visit, i)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 
     * @param map
     * @param visit -> 초기값 0, cyclic -1, acyclic 1
     * @param i -> 과목번호
     * @return
     */
    private boolean canFinishDFS(Map<Integer, List<Integer>> map, int[] visit, int i) {
        // 이미 방문했고 cyclic
        // recursion을 돌다가 여기서 return false를 할 수 있음
        if (visit[i] == -1) {
            return false;
        }
        // 이미 방문했고 acyclic
        // recursion을 돌다가 여기서 return true를 할 수 있음
        if (visit[i] == 1) {
            return true;
        }

        visit[i] = -1;

        // 선수과목이 map에 포함되어 있다면
        if (map.containsKey(i)) {
            
            // 선수과목에 연결된 과목 리스트를 loop
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
     * @param prerequisites[][] -> new prerequisites[5][1]
     * 1. prerequisites[n][0]=1 -> 선수과목
     * 2. prerequisites[n][1]=2 -> 선택과목
     * @return return [true] if you can finish all courses
     */
    public boolean sol2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // 어떤 선수과목이 몇번이나 연결되었나?
        int[] preCounter = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            preCounter[prerequisites[i][0]]++;
        }

        // 연결이 없는 선수과목을 queue에 넣는다.
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (preCounter[i] == 0) {
                queue.add(i);
            }
        }

        // 연결이 없는 선수과목의 개수
        int numNoPre = queue.size();

        while (!queue.isEmpty()) {

            // 선택과목이 없는 선수과목(head)을 pop한다.
            int head = queue.remove();

            for (int i = 0; i < prerequisites.length; i++) {

                // (*) queue에서 pop한 선수과목(head)이 i-th 과목의 선택과목과 일치하면
                if (head == prerequisites[i][1]) {
                    
                    // preCounter를 하나 뺀다.
                    preCounter[prerequisites[i][0]]--;

                    // 선수 과목이 없는 상태가 되면
                    if (preCounter[prerequisites[i][0]] == 0) {
                        // queue에 넣는다.
                        queue.add(prerequisites[i][0]);
                        // 선수 과목이 없는 과목 개수를 1 늘려준다.
                        numNoPre++;
                    }
                }
            }
        }

        return (numNoPre == numCourses);
    }

    public static void main(String[] args) {
        CourseSchedule t = new CourseSchedule();

        //int numCourses = 2;
        //int[][] prerequisites = { {1, 0}, {0, 1} };

        int numCourses = 5; // 0 -> 4
        int[][] prerequisites = { {1, 0}, {2, 1}, {3, 2}, {4, 3} };

        System.out.println(t.sol2(numCourses, prerequisites));
    }


}
