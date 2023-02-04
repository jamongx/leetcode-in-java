package we;

public class MergeTwoSortedLists {
	
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

		if (list1 == null || list2 == null)
			return list1 == null ? list2 : list1;
        System.out.println("input : "+list1.val +" "+list2.val);                
		if (list1.val > list2.val) {
			System.out.println(list1.val + " "+ list2.val);

			ListNode temp = list1;
			list1 = list2;
			list2 = temp;
		}
        
        System.out.println("input2 : "+list1.val +" "+list2.val);                
		list1.next = mergeTwoLists(list1.next, list2);
        System.out.println("result : "+list1.next.val);                
		return list1;
	}
	
	// Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
