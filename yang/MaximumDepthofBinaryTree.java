public class MaximumDepthofBinaryTree {


    public int sol1(TreeNode root) {
        if(root==null)
          return 0;

        int leftDepth = sol1(root.left);
        int rightDepth = sol1(root.right);
        int bigger = Math.max(leftDepth, rightDepth);
        return bigger+1;
    }

    public int sol2(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(sol2(root.left), sol2(root.right));
    }
}
