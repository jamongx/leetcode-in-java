import java.util.Stack;

public class SameTree {

    public boolean sol1(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        Stack<TreeNode> sk1 = new Stack<>();
        Stack<TreeNode> sk2 = new Stack<>();

        sk1.push(p);
        sk2.push(q);

        while (!sk1.isEmpty() && !sk2.isEmpty()) {
            TreeNode curr1 = sk1.pop();
            TreeNode curr2 = sk2.pop();

            if (curr1 == null && curr2 == null) {
                continue; // @note: missed both null check
            } else if (curr1 == null && curr2 != null) {
                return false;
            } else if (curr1 != null && curr2 == null) {
                return false;
            } else if (curr1.val != curr2.val) {
                return false;
            }

            sk1.push(curr1.left);
            sk2.push(curr2.left);

            sk1.push(curr1.right);
            sk2.push(curr2.right);
        }

        // final check
        if (!sk1.isEmpty() || !sk2.isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean sol2(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val &&
                sol2(p.left, q.left) &&
                sol2(p.right, q.right);
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
        System.out.println("Same tree: " + t.sol2(p, q));
    }
}
