package we;

public class LinkedListCycle {

	public boolean hasCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
            System.out.println(slow.val +" "+ fast.val);

			if (slow == fast)
				return true;
		}

		return false;
	}

	// Definition for singly-linked list.
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	} 
	
}

