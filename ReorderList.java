
public class ReorderList {

    public void sol1(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = findMid(head);
        ListNode reversed = reverse(mid);
        merge(head, reversed);
    }
    
    private ListNode findMid(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
    private void merge(ListNode list1, ListNode list2) {

        // list2를 기준으로 loop를 돈다.
        while (list2 != null) {

            //list1.next를 temp에 임시로 저장하고
            ListNode temp = list1.next;

            // list1.next에 list2를 연결한다.
            list1.next = list2;

            // list1를 list2를 넣는다.
            // 위의 list1.next는 list2.next와 같다.
            list1 = list2;

            // list2에 temp(list1.next를) 설정한다.
            // 위의 list1.next = list2는 -> list2.next = list1.next와 같다.
            list2 = temp;
        }
    }

    public static void main(String[] args) {

        ReorderList t = new ReorderList();

        ListNode n1 = new ListNode(1, new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        Utils.printListNode(n1);
        t.sol1(n1);
        Utils.printListNode(n1);
    }

 

}