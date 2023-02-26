package we;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import we.SameTree.TreeNode;

public class SerializeAndDeserializeBinaryTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null)
			return "";

		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));

		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				sb.append("#,");
			} else {
				sb.append(node.val).append(",");
				q.offer(node.left);
				q.offer(node.right);
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.equals(""))
			return null;

		final String[] vals = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));

		for (int i = 1; i < vals.length; i += 2) {
			TreeNode node = q.poll();
			if (!vals[i].equals("#")) {
				node.left = new TreeNode(Integer.parseInt(vals[i]));
				q.offer(node.left);
			}
			if (!vals[i + 1].equals("#")) {
				node.right = new TreeNode(Integer.parseInt(vals[i + 1]));
				q.offer(node.right);
			}
            System.out.println("left : "+(node.left !=null?node.left.val:null) +"   "+"right : "+(node.right !=null?node.right.val:null));
            System.out.println("-------------------------");			
		}

		return root;
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