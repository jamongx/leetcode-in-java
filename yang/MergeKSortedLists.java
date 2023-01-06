import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    /*
     * Time: log(k) * n. k is number of list and n is number of total elements.
     */
    public ListNode sol1(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (final ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        while (!minHeap.isEmpty()) {

            ListNode minNode = minHeap.poll();

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
