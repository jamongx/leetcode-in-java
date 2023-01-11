public class LowestCommonAncestorofaBinarySearchTree {

    /**
     * Recursion
     * Binary search tree: left < parent < right
     * https://walkccc.me/LeetCode/problems/0235/
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode sol1(TreeNode root, TreeNode p, TreeNode q) {
        
        // root.val이 p 와 q 둘 중의 큰 값보다 클때
        // root 보다 작은 left 로 recursion을 돈다.
        if (root.val > Math.max(p.val, q.val)) {
            System.out.println("max, root.val=" +root.val +", p.val=" +p.val +", q.val=" +q.val);
            return sol1(root.left, p, q);
        }
        
        // p 와 q 둘 중의 작은 값보다 root.val이 작을때
        // root 보다 큰 right 로 recursion을 돈다.
        if (root.val < Math.min(p.val, q.val)) {
            System.out.println("min, root.val=" +root.val +", p.val=" +p.val +", q.val=" +q.val);
            return sol1(root.right, p, q);
        }
        
        System.out.println("not, root.val=" +root.val +", p.val=" +p.val +", q.val=" +q.val);
        return root;
    }

    /**
     * Iterator
     * Binary search tree: left < parent < right
     * https://www.programcreek.com/2014/07/leetcode-lowest-common-ancestor-of-a-binary-search-tree-java/
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode sol2(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode temp = root;

        while (temp != null) {
            if (temp.val > Math.max(p.val, q.val)) {
                temp = temp.left;
            }
            else if (temp.val < Math.min(p.val, q.val)) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {

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

        LowestCommonAncestorofaBinarySearchTree t
         = new LowestCommonAncestorofaBinarySearchTree();

        System.out.println("Lowest Common Ancestor: " + t.sol1(root, p, q).val);
    }

}
