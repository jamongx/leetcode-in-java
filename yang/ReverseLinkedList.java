/**
 * https://www.programcreek.com/2014/05/leetcode-reverse-linked-list-java/
 */
public class ReverseLinkedList {

    public ListNode sol1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode second = head.next; // get second node

        head.next = null; // set first's next to be null
        ListNode rest = sol1(second);
        second.next = head;
        return rest;
    }

    public ListNode sol2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p1 = head;
        ListNode p2 = p1.next;
        head.next = null;
        while (p1 != null && p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        return p1;
    }

    public static void main(String[] args) {
        // Input:  [1, 2, 3, 4, 5]
        // Output: [5, 4, 3, 2, 1]
        ReverseLinkedList t = new ReverseLinkedList();

        ListNode node = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(4,
                        new ListNode(5)))));

        System.out.println(t.sol1(node));

    }
}