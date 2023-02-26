package we;

public class MaximumDepthOfBinaryTree {
	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
        int depth = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        System.out.println("Depth : "+depth);
        System.out.println("Value : "+ root.val );
		return depth;
	}
}

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}