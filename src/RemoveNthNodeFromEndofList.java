public class RemoveNthNodeFromEndofList {


    /**
     * Calculate the length first, and then remove the nth from the beginning.
     * @param head
     * @param n
     * @return
     */
    public ListNode sol1(ListNode head, int n) {

        if (head == null) {
            return null;
        }

        // get length of list
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        // if remove first node
        int fromStart = len - n + 1;
        if (fromStart == 1) {
            return head.next;
        }

        // remove non-first node
        p = head;
        int i = 0;
        while (p != null) {
            i++;
            if (i == fromStart - 1) {
                p.next = p.next.next;
            }
            p = p.next;
        }
        return head;
    }


    /**
     * 
     */
    public ListNode sol2(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        // fast pointer를 n칸 앞으로 전진
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 첫번째 node를 삭제하는 경우
        if (fast == null) {
            return head.next;
        }

        // fast.next가 null이면 끝에 다다랐다.
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow.next를 slow.next.next로 연결한다.
        // k번째 node를 삭제하는 것이다.
        slow.next = slow.next.next;

        return head;
    }


    public static void main(String[] args) {
        RemoveNthNodeFromEndofList t = new RemoveNthNodeFromEndofList();

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8)))));
        ListNode list2 = new ListNode(3, new ListNode(5, new ListNode(7, new ListNode(9, new ListNode(11)))));

        // System.out.println(t.sol1(list1, list2));
        System.out.println(t.sol2(list1, 2));
        System.out.println(t.sol2(list2, 2));
    }
}
