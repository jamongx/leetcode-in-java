import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

    public TreeNode sol1(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left  = root.left;
        TreeNode right = root.right;

        root.left  = sol1(right);
        root.right = sol1(left);
        return root;
    }


    public TreeNode sol2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {

            TreeNode p = queue.poll();
            if (p.left != null) {
                queue.add(p.left);
            }

            if (p.right != null) {
                queue.add(p.right);
            }

            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }
        return root;
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

        System.out.println("Invert Binary tree before: ");
        Utils.printBinaryTree(root);

        InvertBinaryTree t = new InvertBinaryTree();
        System.out.println("Invert Binary tree after: ");
        Utils.printBinaryTree(t.sol1(root));
    }
}
