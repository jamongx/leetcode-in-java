public class BinaryTreeMaximumPathSum {

    // path -> 위로 올리는 값
    // path sum -> 각 node에서 right + left + root 최대값
    private int result = Integer.MIN_VALUE;

    public int sol1(TreeNode root) {
        maxPathSumDownFrom(root);
        return result;
    }

    // root->val + 0/1 of its subtrees
    private int maxPathSumDownFrom(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // left or right subtree의 값이 negative이면 버린다.
        int left  = Math.max(maxPathSumDownFrom(root.left),  0);
        int right = Math.max(maxPathSumDownFrom(root.right), 0);
        
        // if left and right child are both negative
        result = Math.max(result, root.val + left + right);

        // max(left, right)와 root.val를 더한값을 return
        return root.val + Math.max(left, right);
    }



    public int sol2(TreeNode root) {
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



    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(7);

        BinaryTreeMaximumPathSum t = new BinaryTreeMaximumPathSum();
        System.out.println(t.sol1(root));
    }
}
