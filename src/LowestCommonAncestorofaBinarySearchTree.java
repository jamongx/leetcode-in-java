public class LowestCommonAncestorofaBinarySearchTree {

    /**
     * BST: left < mid < right
     * Recursion
     * DFS
     * TC: O(h)
     * SC: O(h)
     */
    public TreeNode sol1(TreeNode root, TreeNode p, TreeNode q) {
        
        if (root.val > Math.max(p.val, q.val)) {
            return sol1(root.left, p, q);
        }
        
        if (root.val < Math.min(p.val, q.val)) {
            return sol1(root.right, p, q);
        }
        
        // root <= max(p, q) and root >= min(p, q)
        // 즉 root는 p와 q의 중간에 있다.
        return root;
    }

    /**
     * Iterative
     * TC: O(h)
     * SC: O(h)
     */
    public TreeNode sol2(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode pred = root;

        while (pred != null) {
            if (pred.val > Math.max(p.val, q.val)) {
                pred = pred.left;
            }
            else if (pred.val < Math.min(p.val, q.val)) {
                pred = pred.right;
            } else {
                return pred;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        LowestCommonAncestorofaBinarySearchTree t
         = new LowestCommonAncestorofaBinarySearchTree();

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = new TreeNode(0);
        TreeNode q = new TreeNode(5);


        System.out.println("Lowest Common Ancestor: " + t.sol1(root, p, q).val);
    }

}
