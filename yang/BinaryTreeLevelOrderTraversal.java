import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> sol1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>(Arrays.asList(root));

        while (!queue.isEmpty()) {

            List<Integer> level = new ArrayList<>();

            // int i = queue.size() 초기 값은
            // for loop가 끝날때까지 값이 변경되지 않는다.
            // 즉 queue의 push한 node 개수 (=parent)만큼 loop를 돌면서
            // 그 parent의 child를 queue에 push한다.
            for (int i = queue.size(); i > 0; i--) {

                TreeNode node = queue.poll();

                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
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
