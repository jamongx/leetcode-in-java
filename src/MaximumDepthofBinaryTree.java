import java.util.Queue;
import java.util.LinkedList;

public class MaximumDepthofBinaryTree {

    public int sol1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);// @note: use null as marker for end of level

        int depth = 0;
        while (!queue.isEmpty()) {
            // 1. root -> null 
            TreeNode current = queue.poll();

            // 2. curr = root, null이 나오면 level의 끝을 의미한다.
            if (current == null) {
                depth++;
                if (!queue.isEmpty()) {
                    // 4. q = left -> right -> null
                    // binary tree이기 때문에 가능하다
                    queue.offer(null);
                }
                continue;
            }

            // 3. q = null -> left -> right
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return depth;
    }


    public int sol2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(sol2(root.left), sol2(root.right));
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(7);
        Utils.printBinaryTree(root);

        MaximumDepthofBinaryTree t = new MaximumDepthofBinaryTree();
        System.out.println("Max depth: " + t.sol1(root));
        System.out.println("Max depth: " + t.sol2(root));
    }
}
