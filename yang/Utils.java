import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static void printArray(int[] array) {
         System.out.println(Arrays.toString(array));
    }

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

    public static void printListNode(ListNode n) {
        System.out.println("------");
        while (n != null) {

            System.out.print(n.val);
            if(n.next != null) {
                System.out.print(", ");
            }
            n = n.next;
        }
        System.out.println();
    }
    
    public static void printUGraph(UndirectedGraphNode node) {
        System.out.println("------");
        
        Set<UndirectedGraphNode> set = new HashSet<>();
        DFS(node, set);
    }

    private static void DFS(UndirectedGraphNode node, Set<UndirectedGraphNode> set) {
        if (set.contains(node)) {
             return;
        }

        set.add(node);
        System.out.println(node);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            DFS(neighbor, set);
        }
    }

    public static void printBinaryTree(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            printBinaryTree(root.left);
            printBinaryTree(root.right);
        }
    }


}
