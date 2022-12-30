public class LowestCommonAncestorofaBinarySearchTree {

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode m = root;

        if (m.val > p.val && m.val < q.val) {
            return m;
        } else if (m.val > p.val && m.val > q.val) {
            return lowestCommonAncestor1(root.left, p, q);
        } else if (m.val < p.val && m.val < q.val) {
            return lowestCommonAncestor1(root.right, p, q);
        }

        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode t = root;
        while (t != null) {
            if (p.val > t.val && q.val > t.val) {
                t = t.right;
            } else if (p.val < t.val && q.val < t.val) {
                t = t.left;
            } else {
                return t;
            }
        }
        return null;
    }
}
