import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class KthSmallestElementinaBST {
    public int kthSmallest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int result = 0;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode t = stack.pop();
                k--;
                if (k == 0)
                    result = t.val;
                p = t.right;
            }
        }
        return result;
    }
    
    private int ans = -1;
    private int rank = 0;

    public int kthSmallest2(TreeNode root, int k) {
        traverse(root, k);
        return ans;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null)
            return;

        traverse(root.left, k);
        if (++rank == k) {
            ans = root.val;
            return;
        }
        traverse(root.right, k);
    }

    public int kthSmallest3(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        for (int i = 0; i < k - 1; ++i) {
            root = stack.pop();
            root = root.right;
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        return stack.peek().val;
    }

    public int kthSmallest4(TreeNode root, int k) {
        final int leftCount = countNodes(root.left);

        if (leftCount == k - 1)
            return root.val;
        if (leftCount >= k)
            return kthSmallest4(root.left, k);
        return kthSmallest4(root.right, k - 1 - leftCount); // LeftCount < k
    }

    private int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}
