public class MergeTwoSortedLists {

    public ListNode sol1(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // list1와 list2를 순회한다.
        while (list1 != null || list2 != null) {
            int val1 = (list1 == null ? Integer.MAX_VALUE : list1.val);
            int val2 = (list2 == null ? Integer.MAX_VALUE : list2.val);

            if (val1 < val2) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }

            // curr.next로 이동한다.
            // now curr is the new end node, but still pointing to next node
            curr = curr.next;
            // curr.next.next를 null로 set한다.
            // @note: key, cut this node from list1 or list2
            curr.next = null;
        }

        return dummy.next;
    }


    public ListNode sol2(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        // list1를 기준으로 merge 한다.
        // list1.val의 값이 list2.val 보다 작아야 한다.
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

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8)))));
        ListNode list2 = new ListNode(3, new ListNode(5, new ListNode(7, new ListNode(9, new ListNode(11)))));


        //System.out.println(t.sol1(list1, list2));
        System.out.println(t.sol2(list1, list2));
    }
}
