public class SameTree {

    public boolean sol1(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        return p.val == q.val && sol1(p.left, q.left) && sol1(p.right, q.right);
    }

    public static void main(String[] args) {

        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.right.left = new TreeNode(5);
        p.right.right = new TreeNode(6);
        p.right.right.left = new TreeNode(8);
        p.right.left.right = new TreeNode(7);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        q.left.left = new TreeNode(4);
        q.right.left = new TreeNode(5);
        q.right.right = new TreeNode(6);
        q.right.right.left = new TreeNode(8);
        q.right.left.right = new TreeNode(7);

        SameTree t = new SameTree();

        System.out.println("Same tree: " + t.sol1(p, q));
    }
}
