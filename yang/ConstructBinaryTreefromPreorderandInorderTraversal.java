import java.util.HashMap;
import java.util.Map;

/**
 * 트리의 전위, 중위 순회로 결과를 입력값으로 받아 이진 트리를 구축
 * - preorder의 첫번째 값은 root 노드가 되며
 * - inorder의 root를 기준으로 left 와 right 로 나누는 역할을 한다.
 * 
 * preorder traversal: mid -> left -> right
 * inorder  traversal: left -> mid -> right
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode sol1(int[] preorder, int[] inorder) {

        // inorder 배열을 map에 index와 같이 저장한다.
        Map<Integer, Integer> inToIndex = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inToIndex.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1,
                     inorder,  0, inorder.length  - 1,
                     inToIndex);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder,  int inStart,  int inEnd,
                           Map<Integer, Integer> inToIndex) {

        if (preStart > preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];

        // rootInIndex -> inorder에서 root의 위치의 index
        int rootInIndex = inToIndex.get(rootVal);

        // inorder에서 rootVal을 중심으로 left 파트
        int leftSize = rootInIndex - inStart;

        // preorder은 preStart와 preEnd에 leftSize를 더하여 left와 right를 나눈다.
        // inorder은 rootInIndex로 inStart와 inEnd를 만든다.
        TreeNode root = new TreeNode(rootVal);
        root.left  = build(preorder, preStart + 1, preStart + leftSize,
                           inorder, inStart, rootInIndex - 1,
                           inToIndex);

        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                           inorder, rootInIndex + 1, inEnd,
                           inToIndex);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreefromPreorderandInorderTraversal t
         = new  ConstructBinaryTreefromPreorderandInorderTraversal();

        // preorder 배열과 inorder 배열이 같은 treenode 값을 저장하고 있다.
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };

        Utils.printBinaryTree(t.sol1(preorder, inorder));
    }
}
