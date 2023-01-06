public class MergeTwoSortedLists {

    public ListNode sol1(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        ListNode p1 = list1;
        ListNode p2 = list2;

        while (p1 != null && p2 != null) {

            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            }
            else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return head.next;

    }


    public ListNode sol2(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null)
            return list1 == null ? list2 : list1;
        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        list1.next = sol2(list1.next, list2);
        return list1;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists t = new MergeTwoSortedLists();

        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8)))));
        ListNode node2 = new ListNode(3, new ListNode(5, new ListNode(7, new ListNode(9, new ListNode(11)))));


        System.out.println(t.sol1(node1, node2));
    }
}
