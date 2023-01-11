import java.util.HashMap;
import java.util.Map;

/**
 * 트리의 전위, 중위 순회로 결과를 입력값으로 받아 이진 트리를 구축
 * - 전위의 첫번 째 값은 부모 노드이며 중위 순회 결과를 left 와 right 로 나누는 역할을 한다.
 * - 즉, 전위 순회의 값을 순차적으로 빼면서 중위 순회를 left 와 right 로 나눈다.
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    /**
     * preorder 배열로 root를 만들고
     * inorder 배열로 left, right를 만든다.
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode sol1(int[] preorder, int[] inorder) {

        // inorder 배열을 map에 index와 같이 저장한다.
        Map<Integer, Integer> inToIndex = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inToIndex.put(inorder[i], i);
        }

        return build(preorder,
                         0, // preorder의 시작
                         preorder.length - 1, // preorder의 마지막 (배열의끝)
                     inorder,
                         0, // inorder 시작
                         inorder.length - 1, // inorder의 마지막 (배열의 끝) 
                     inToIndex);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder,  int inStart,  int inEnd,
                           Map<Integer, Integer> inToIndex) {

        if (preStart > preEnd) {
            return null;
        }

        // preorder는 root를 먼저 방문한다.
        // preorder는 <중간 -> 왼쪽 -> 오른쪽> 순서로 traversal을 한다.
        // rootVal -> 3 (preorder[0]=3)
        int rootVal     = preorder[preStart];

        // rootInIndex -> inorder에서 root의 위치의 index
        // inorder는 <왼쪽 -> 중간 -> 오른쪽> 순서로 traversal을 한다.
        int rootInIndex = inToIndex.get(rootVal);

        // inorder 배열의 rootVal 값의 index에서 inStart 값을 빼준다.
        int leftSize    = rootInIndex - inStart;

        TreeNode root = new TreeNode(rootVal);
        // 마지막 node라면 left 또는 right가 null인 TreeNode를 return 한다.
        root.left  = build(preorder,
                               preStart + 1,
                               preStart + leftSize,
                           inorder,
                               inStart,
                               rootInIndex - 1,
                           inToIndex);

        root.right = build(preorder,
                               preStart + leftSize + 1,
                               preEnd,
                           inorder,
                               rootInIndex + 1,
                               inEnd,
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
