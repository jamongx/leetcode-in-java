public class SubtreeofAnotherTree {

    public boolean sol1(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }

        if (isSameTree(s, t)) {
            return true;
        }

        return sol1(s.left, t) || sol1(s.right, t);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        TreeNode sub = new TreeNode(4);
        sub.left = new TreeNode(1);
        sub.right = new TreeNode(2);

        SubtreeofAnotherTree t = new SubtreeofAnotherTree();

        System.out.println(t.sol1(root, sub));

    }
}
