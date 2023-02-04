import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree {

    /**
     * BFS
     */
    public String serialize1(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>(Arrays.asList(root));

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("n ");
            }
            else {
                sb.append(node.val).append(" ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return sb.toString();
    }

    /**
     * BFS
     */
    public TreeNode deserialize1(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] vals = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>(Arrays.asList(root));
        for (int i = 1; i < vals.length; i += 2) {

            TreeNode node = queue.poll();
            if (!vals[i].equals("n")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(node.left);
            }
            if (!vals[i + 1].equals("n")) {
                node.right = new TreeNode(Integer.parseInt(vals[i + 1]));
                queue.offer(node.right);
            }
        }
        return root;
    }


    /**
     * DFS
     */
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("n ");
            return;
        }

        sb.append(root.val).append(" ");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    /**
     * DFS
     */
    public TreeNode deserialize2(String data) {
        String[] vals = data.split(" ");
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(vals));
        return preorder(queue);
    }

    private TreeNode preorder(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("n")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = preorder(queue);
        root.right = preorder(queue);
        return root;
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

        SerializeandDeserializeBinaryTree t = new SerializeandDeserializeBinaryTree();

        String serial = t.serialize1(root);
        System.out.println("Serial=" +serial);
        Utils.printBinaryTree(t.deserialize1(serial));
    }

}
