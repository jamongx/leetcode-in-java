import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class SerializeandDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode h = q.poll();
            if (h == null) {
                list.add("#");
            } else {
                list.add("" + h.val);
                q.offer(h.left);
                q.offer(h.right);
            }
        }
        return String.join(",", list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        String[] arr = data.split(",");
        if (arr[0].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode h = q.poll();
            if (h != null) {
                TreeNode left = null;
                if (!arr[i].equals("#")) {
                    left = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.left = left;
                q.offer(left);
                i++;
                TreeNode right = null;
                if (!arr[i].equals("#")) {
                    right = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.right = right;
                q.offer(right);
                i++;
            }
        }
        return root;
    }


    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode h = stack.pop();
            if (h != null) {
                sb.append(h.val + ",");
                stack.push(h.right);
                stack.push(h.left);
            } else {
                sb.append("#,");
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if (data == null)
            return null;
        int[] t = { 0 };
        String[] arr = data.split(",");
        return helper(arr, t);
    }

    public TreeNode helper(String[] arr, int[] t) {
        if (arr[t[0]].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[t[0]]));
        t[0] = t[0] + 1;
        root.left = helper(arr, t);
        t[0] = t[0] + 1;
        root.right = helper(arr, t);
        return root;
    }
}
