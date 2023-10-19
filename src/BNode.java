// define a BNode class with TreeNode and it's boundaries
public class BNode {
    TreeNode n;
    double left;
    double right;

    public BNode(TreeNode n, double left, double right) {
        this.n = n;
        this.left = left;
        this.right = right;
    }
}