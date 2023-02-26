package we;

import we.SameTree.TreeNode;

public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
		maxPathSumDownFrom(root);
		return ans;
	}

	private int ans = Integer.MIN_VALUE;

	// root->val + 0/1 of its subtrees
	private int maxPathSumDownFrom(TreeNode root) {
		if (root == null)
			return 0;

		final int l = Math.max(maxPathSumDownFrom(root.left), 0);
		final int r = Math.max(maxPathSumDownFrom(root.right), 0);
		ans = Math.max(ans, root.val + l + r);
		System.out.println("ans : "+ ans +"   "+"root.val : "+ root.val + "   "+"l : "+ l +"   "+"r : "+ r);
		return root.val + Math.max(l, r);
	}
	
	//Definition for a binary tree node.
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
}

//Definition for a binary tree node.
//class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//
//	TreeNode() {
//	}
//
//	TreeNode(int val) {
//		this.val = val;
//	}
//
//	TreeNode(int val, TreeNode left, TreeNode right) {
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}
//}
