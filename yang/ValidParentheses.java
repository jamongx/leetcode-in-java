import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    /**
     * https://www.programcreek.com/2012/12/leetcode-valid-parentheses-java/
     * A typical problem which can be solved by using a stack data structure.
     * @param s
     * @return
     */
    public boolean sol1(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (char curr : s.toCharArray()) {

            // 머리가 나오면
            if (map.keySet().contains(curr)) {
                stack.push(curr);
            }
            // 꼬리가 나오면
            else if (map.values().contains(curr)) {
                if (!stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    /**
     * https://walkccc.me/LeetCode/problems/0020/
     * TC: O(n)
     * OC: O(n)
     * @param s
     * @return
     */
    public boolean sol2(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            System.out.println("c=" +c);
            if (c == '(') {
                stack.push(')');
            }
            else if (c == '{') {
                stack.push('}');
            }
            else if (c == '[') {
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
            System.out.println("stack=" +stack);
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();

        String s = "()[]{}";

        System.out.println(v.sol2(s));
    }
}
