public class BinaryTreeMaximumPathSum {

    public int sol1(TreeNode root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }

        int left  = calculateSum(root.left,  max);
        int right = calculateSum(root.right, max);

        int root_left_or_right = Math.max(root.val + left, root.val + right);

        int current = Math.max(root.val, root_left_or_right);

        max[0] = Math.max(max[0], Math.max(current, left + root.val + right));
        return current;
    }

    private int ans = Integer.MIN_VALUE;

    public int sol2(TreeNode root) {
        maxPathSumDownFrom(root);
        return ans;
    }

    // root->val + 0/1 of its subtrees
    private int maxPathSumDownFrom(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left  = Math.max(maxPathSumDownFrom(root.left),  0);
        int right = Math.max(maxPathSumDownFrom(root.right), 0);

        ans = Math.max(ans, root.val + left + right);

        return root.val + Math.max(left, right);
    }
}
