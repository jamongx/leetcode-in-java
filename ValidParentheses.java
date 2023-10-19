import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    /**
     * https://walkccc.me/LeetCode/problems/0020/
     * TC: O(n)
     * OC: O(n)
     * 1. stack을 사용한다.
     * 2. '(' 나오면 ')'를 stack에 push 한다.
     * 3. 다음 문자(')')가 stack에서 pop한 문자와 같으면 true
     */
    public boolean sol1(String s) {
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


    /**
     * https://www.programcreek.com/2012/12/leetcode-valid-parentheses-java/
     * A typical problem which can be solved by using a stack data structure.
     * 1. map과 stack을 사용한다.
     * 2. map에 parenthesis를 key, value 넣는다.
     * 3. head('(')가 나오면 stack에 push하고
     * 4. tail(')')가 나오면 stack에서 peek해서 같으면 pop한다.
     * 5. stack의 사이즈가 empty이면 true
     */
    public boolean sol2(String s) {

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

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();

        //String s = "()[]{}";
        String s = "({})";

        System.out.println(v.sol1(s));
    }
}
