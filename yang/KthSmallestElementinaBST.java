import java.util.Deque;
import java.util.ArrayDeque;

public class KthSmallestElementinaBST {

    /**
     * Binary Search 
     * 가장 작은걸 찾아서 밑에서 위로 올라오면서 k-th를 찾는 것이다.
     * TC: O(n^2)
     * SC: O(h)
     */
    public int sol1(TreeNode root, int k) {
        // leftCount -> left (mid 보다 작은) node 개수
        int leftCount = countNodes(root.left);

        if (leftCount + 1 == k) {
            return root.val;
        }
        if (leftCount + 1 > k) {
            // left로 1단계 내려간다.
            // sol1에서 left_count를 다시 센다 (1단계 더 내려갈 수도 있다)
            return sol1(root.left, k);
        }

        // leftCount가 K보다 작으면 
        // left와 root의 개수는 k에서 빼주고 right side를 다시 찾는다.
        return sol1(root.right, k - 1 - leftCount);
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Inorder Traversal: left -> mid -> right
     * TC: O(n)
     * SC: O(h)
     */
    public int sol2(TreeNode root, int k) {
        
        int[] temp = new int[3];
        temp[0] = k;  // k-th
        temp[1] = -1; // result
        temp[2] = 0;  // rank

        traverse(root, temp);
        return temp[1];
    }

    private void traverse(TreeNode root, int[] temp) {
        if (root == null) {
            return;
        }

        // 1) left 비교하고
        traverse(root.left, temp);

        // 2) mid 비교한다.
        // rank와 krk 같으며 return
        if (++temp[2] == temp[0]) {
            temp[1] = root.val;
            return;
        }

        // 3) right 비교한다.
        traverse(root.right, temp);
    }

    /**
     * Stack을 사용한다.
     * ValidateBinarySearchTree과 동일한 알고리즘을 사용한다.
     * TC: O(n)
     * SC: O(h)
     */
    public int sol3(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        for (int i = 0; i < k - 1; i++) {
            root = stack.pop();
            root = root.right;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        return stack.peek().val;
    }


    /**
     * Extra Data Structure
     * We can let each node track the order, i.e.,
     * the number of elements that are less than itself. Time is O(log(n)).
     */
    public int sol4(TreeNode root, int k) {
        return 0;
    }

    public static void main(String[] args) {

        TreeNode one = new TreeNode(3);
        one.left = new TreeNode(1);
        one.right = new TreeNode(4);
        one.left.right = new TreeNode(2);

		TreeNode two = new TreeNode(5);
        two.left = new TreeNode(3);
        two.right = new TreeNode(6);
        two.left.left = new TreeNode(2);
        two.left.right = new TreeNode(4);
        two.left.left.left = new TreeNode(1);

        KthSmallestElementinaBST t = new KthSmallestElementinaBST();

        //System.out.println("Kth Smallest Element in a BST: " + t.sol1(one, 1));
        System.out.println("Kth Smallest Element in a BST: " + t.sol1(two, 3));
    }

}
