public class ListNode {

    public int val;
    public ListNode head = null;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(val);

        ListNode iter = next;
        while (iter != null) {
            str.append(", ");
            str.append(iter.val);
            iter = iter.next;
        }
        return str.toString();
    }
}