public class ReverseLinkedList {

    // recursion이기 때문에 끝까지 갔다가 되돌아오면서 node를 순차적으로 처리한다.
    public ListNode sol1(ListNode head) {
        if (head == null || head.next == null) {
            System.out.println("the end of nodes: " +head +", &head=" +System.identityHashCode(head));
            return head;
        }

        // newHead는 reverse list의 마지막 node를 가르키는 reference이다.
        // recursion을 타고 계속 올라간다.
        ListNode newHead = sol1(head.next);
        System.out.println("head=" +head.val +", newHead=" +newHead.val);

        // 현재 head(4) 그리고 head.next(5) -> head.next(5).next를 head(4)로 바꿔준다.
        head.next.next = head;
        System.out.println("head=" +head.val +", head.next.next=" +head.next.next.val);
        System.out.println("$newHead=" +System.identityHashCode(newHead) +", &head.next=" +System.identityHashCode(head.next));

        // head(4).next 를 null로 set한다.
        head.next = null;
        System.out.println("head=" +head.val +", head.next = null");

        System.out.println(newHead);
        return newHead;
    }


    public ListNode sol2(ListNode head) {
        //loop를 돌기 때문에 조건문은 필요 없다.
        //if (head == null || head.next == null) {
        //    return head;
        //}

        ListNode pred = null; // pointer 이전의 node pointer

        while (head != null) { // head는 loop 포인터

            System.out.println("head.val=" +head.val);

            ListNode next = head.next; // 다음 pointer를 next에 보관해 둔다.

            head.next = pred; // head.next를 앞의 것을 가리키도록 한다.
            if(head.next!=null)
                System.out.println("head.next=" +head.next.val);
            else
                System.out.println("pred=null");

            pred = head; // pred (앞의 node)를 head로 한칸 전진한다.
            System.out.println("pred=" +pred.val);

            head = next; // 보관해둔 다음 pointer로 복사한다.

        }
        return pred;
    }

    public static void main(String[] args) {
        ReverseLinkedList t = new ReverseLinkedList();
        
        // [1, 2, 3, 4, 5]
        ListNode node = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3,
                        new ListNode(4,
                        new ListNode(5)))));

        System.out.println(node);
        System.out.println(t.sol1(node));
    }
}