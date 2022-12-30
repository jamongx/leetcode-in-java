import java.util.LinkedList;

public class InvertBinaryTree {

    public TreeNode sol1(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode n) {
        if (n == null) {
            return;
        }
        TreeNode t = n.left;
        n.left = n.right;
        n.right = t;
        helper(n.left);
        helper(n.right);
    }

    public TreeNode sol2(TreeNode root) {
        if (root == null) {
            return root;
        }
        sol2(root.left);
        sol2(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        return root;
    }


    public TreeNode sol3(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left != null)
                queue.add(p.left);
            if (p.right != null)
                queue.add(p.right);
            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }
        return root;
    }

}
