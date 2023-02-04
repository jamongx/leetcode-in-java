import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBinarySearchTree {

    public boolean sol1(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        // root가 null이면 true를 return한다.
        // leaf 마지막까지 binary search tree 이어야 되므로 끝까지 true가 이어져야 한다.
        if (root == null) {
            return true;
        }

        // minNode가 null이 아니고 root보다 크거나 같으면
        if (minNode != null && root.val <= minNode.val) {
            return false;
        }

        // maxNode가 null이 아니고 root보다 작거나 같으면
        if (maxNode != null && root.val >= maxNode.val) {
            return false;
        }

        // root.left는  root 보다 작다.
        // root.right는 root 보다 크다.
        return isValidBST(root.left,  minNode, root) &&
               isValidBST(root.right, root,    maxNode);
    }


    public boolean sol2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode pred = null; // predecessor

        while (root != null || !stack.isEmpty()) {

            // binary search tree는 root.left가 root보다 작은 값을 갖는다.
            // loop를 돌면서 root와 root.left들을 leaf까지 stack에 넣는다.
            while (root != null) {
                stack.push(root);
                System.out.println("root or root.left=" +root.val);
                root = root.left;
            }

            // stack 이기 때문에
            // 가장 마지막에 들어간 node가 가장 마지막 node
            root = stack.pop();
            
            if (pred == null) {
                System.out.println("pred=null");
            }
            else if(pred.val >= root.val) {
                System.out.println("fail> pred.val=" +pred.val +", root.val=" +root.val);
            }
            else {
                System.out.println("pred.val=" +pred.val +", root.val=" +root.val);
            }

            // 아래 root가 pred가 되고 root.right -> root가 되었으므로,
            // root가 pred 보다 커야 된다.
            if (pred != null && pred.val >= root.val) {
                return false;
            }
            
            pred = root;
            root = root.right;
        }
        return true;
    }


    public static void main(String[] args) {

        ValidateBinarySearchTree t = new ValidateBinarySearchTree();

        TreeNode one = new TreeNode(2);
        one.left = new TreeNode(1);
        one.right = new TreeNode(3);

        TreeNode two = new TreeNode(5);
        two.left = new TreeNode(1);
        two.right = new TreeNode(4);
        two.right.left = new TreeNode(3);
        two.right.right = new TreeNode(6);
 

        System.out.println(t.sol2(one));
        System.out.println(t.sol2(two));
    }
}

