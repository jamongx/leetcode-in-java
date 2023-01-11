public class MaximumDepthofBinaryTree {

    public int sol1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth  = sol1(root.left);
        int rightDepth = sol1(root.right);
        int bigger = Math.max(leftDepth, rightDepth);
        return bigger + 1;
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
        // https://www.baeldung.com/java-print-binary-tree-diagram
        Utils.printBinaryTree(root);

        MaximumDepthofBinaryTree t = new MaximumDepthofBinaryTree();

        System.out.println("Max depth: " + t.sol1(root));
    }
}
