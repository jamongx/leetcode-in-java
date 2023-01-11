import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> sol1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        // 연결되어 있으니 root 하나만 넣어도 된다.
        Queue<TreeNode> queue = new ArrayDeque<>(Arrays.asList(root));

        while (!queue.isEmpty()) {

            System.out.println("while queue.size()=" +queue.size());

            List<Integer> currLevel = new ArrayList<>();

            // int i = queue.size()는 초기값이 한번 설정되면
            // loop가 끝날때까지 값이 변경되지 않는다.
            for (int i = queue.size(); i > 0; i--) {
                System.out.println("for queue.size()=" +queue.size());

                TreeNode node = queue.poll();

                System.out.println("poll queue.size()=" +queue.size());
                currLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                    System.out.println("add left queue.size()=" +queue.size());
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    System.out.println("add right queue.size()=" +queue.size());
                }
            }
            ans.add(currLevel);
        }
        return ans;
    }



    public ArrayList<ArrayList<Integer>> sol2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }


        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();
        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.add(root);

        while (!current.isEmpty()) {
            TreeNode node = current.remove();
            if (node.left != null)
                next.add(node.left);
            if (node.right != null)
                next.add(node.right);
            nodeValues.add(node.val);
            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<TreeNode>();
                al.add(nodeValues);
                nodeValues = new ArrayList<>();
            }
        }
        return al;
    }

    public List<List<Integer>> sol3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        LinkedList<Integer> levelQueue = new LinkedList<>();
        nodeQueue.offer(root);
        levelQueue.offer(1);// start from 1
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();
            List<Integer> l = null;
            if (result.size() < level) {
                l = new ArrayList<>();
                result.add(l);
            } else {
                l = result.get(level - 1);
            }
            l.add(node.val);
            if (node.left != null) {
                nodeQueue.offer(node.left);
                levelQueue.offer(level + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                levelQueue.offer(level + 1);
            }
        }
        return result;
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

        BinaryTreeLevelOrderTraversal t = new BinaryTreeLevelOrderTraversal();

        System.out.println(t.sol1(root));
    }

}
