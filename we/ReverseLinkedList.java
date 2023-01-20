package we;

public class ReverseLinkedList {
	
	public static void main(String args[]) {
		ListNode input = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		
		ListNode result = new ReverseLinkedList().reverseList(input);
		while (result != null) {
			System.out.println(result.val);
			result = result.next;
		}
//		System.out.println(result.val);
//		System.out.println(result.next.val);
//		System.out.println(result.next.next.val);
//		System.out.println(result.next.next.next.val);
//		System.out.println(result.next.next.next.next.val);
		
	}
	
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList(head.next);
		System.out.println(head.val);
		System.out.println(head.next.val);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
}

// Definition for singly-linked list
class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}