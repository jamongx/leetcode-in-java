import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidateBinarySearchTree {

    public boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public boolean isValidBST1(TreeNode p, double min, double max) {
        if (p == null)
            return true;
        if (p.val <= min || p.val >= max)
            return false;
        return isValidBST1(p.left, min, p.val) && isValidBST1(p.right, p.val, max);
    }

    public boolean isValidBST2(TreeNode root) {
        return helper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public boolean helper(TreeNode root, double min, double max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        boolean isLeftBST = helper(root.left, min, root.val);
        boolean isRightBST = helper(root.right, root.val, max);
        if (!isLeftBST || !isRightBST) {
            return false;
        }
        return true;
    }

    public boolean isValidBST3(TreeNode root) {
        if (root == null)
            return true;
        LinkedList<BNode> queue = new LinkedList<BNode>();
        queue.add(new BNode(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        while (!queue.isEmpty()) {
            BNode b = queue.poll();
            if (b.n.val <= b.left || b.n.val >= b.right) {
                return false;
            }
            if (b.n.left != null) {
                queue.offer(new BNode(b.n.left, b.left, b.n.val));
            }
            if (b.n.right != null) {
                queue.offer(new BNode(b.n.right, b.n.val, b.right));
            }
        }
        return true;
    }


    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<StringBuilder> row = new ArrayList<>();
        int rowLetters = 0;

        for (final String word : words) {
            if (rowLetters + row.size() + word.length() > maxWidth) {
                final int spaces = maxWidth - rowLetters;
                if (row.size() == 1) {
                    for (int i = 0; i < spaces; ++i)
                        row.get(0).append(" ");
                } else {
                    for (int i = 0; i < spaces; ++i)
                        row.get(i % (row.size() - 1)).append(" ");
                }
                final String joinedRow = row.stream().map(StringBuilder::toString).collect(Collectors.joining(""));
                ans.add(joinedRow);
                row.clear();
                rowLetters = 0;
            }
            row.add(new StringBuilder(word));
            rowLetters += word.length();
        }

        final String lastRow = row.stream().map(StringBuilder::toString).collect(Collectors.joining(" "));
        StringBuilder sb = new StringBuilder(lastRow);
        final int spacesToBeAdded = maxWidth - sb.length();
        for (int i = 0; i < spacesToBeAdded; ++i)
            sb.append(" ");

        ans.add(sb.toString());
        return ans;
    }
}
