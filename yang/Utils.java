public class Utils {

    public static void print2Darray(int[][] array) {

        int m = array.length;
        int n = array[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", array[i][j]);
            }
            System.out.println();
        }
    }

    public static void printList(ListNode n) {
        System.out.println("------");
        while (n != null) {
            System.out.print(n.val);
            n = n.next;
        }
        System.out.println();
    }

}
