import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    /**
     * https://walkccc.me/LeetCode/problems/0023/
     * Time: O(nlog⁡k)
     * Space: O(k)
     * --------------------------------------------------------------------------
     * https://www.programcreek.com/2013/02/leetcode-merge-k-sorted-lists-java/
     * Time: log(k) * n.
     * k is number of list and n is number of total elements. 
     * 우선순위 queue선언하는것이 포인트이다.
     * @param lists
     * @return
     */
    public ListNode sol1(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;


        /* java8
         * 1. Comparator 이용
         * Comparator<ListNode> comp = Comparator.comparing(ListNode->ListNode.val);
         * PriorityQueue<ListNode> queue = new PriorityQueue<>(comp);
         * 2. 람다식을 이용
         * Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> b.val - a.val); // 오름차순 */
        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val); // 내림차순

        for ( ListNode list : lists) {
            if (list != null) {
                // Priority Queue Add
                minHeap.offer(list);
            }
        }

        while (!minHeap.isEmpty()) {

            // 첫번째 값을 반환하고 제거 비어있다면 null
            ListNode minNode = minHeap.poll();

            // pop한 node의 next가 null이 아니면 다시 add한다.
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }

            curr.next = minNode;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists t = new MergeKSortedLists();

        // an array of pointers storing the head nodes
        // of the linked lists
        ListNode lists[] = new ListNode[3];
 
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(3);
        lists[0].next.next = new ListNode(5);
        lists[0].next.next.next = new ListNode(7);
 
        lists[1] = new ListNode(2);
        lists[1].next = new ListNode(4);
        lists[1].next.next = new ListNode(6);
        lists[1].next.next.next = new ListNode(8);
 
        lists[2] = new ListNode(0);
        lists[2].next = new ListNode(9);
        lists[2].next.next = new ListNode(10);
        lists[2].next.next.next = new ListNode(11);

        System.out.println(t.sol1(lists));
    }
}