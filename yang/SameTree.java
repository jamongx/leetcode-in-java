public class SameTree {

    public boolean sol1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return sol1(p.left, q.left) && sol1(p.right, q.right);
        } else {
            return false;
        }
    }

    public boolean sol2(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == q;
        return p.val == q.val &&
                sol2(p.left, q.left) &&
                sol2(p.right, q.right);
    }

}
