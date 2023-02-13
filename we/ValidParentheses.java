package we;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ValidParentheses {
	public boolean isValid(String s) {
		// Define Deque class to save parentheses in the string
		Deque<Character> stack = new ArrayDeque<>();
		// 해당 괄호의 닫는 괄호를 넣어서 나중에 넣은 것부터 비교한다. (LIFO) ==> Stack
		for (final char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;

			System.out.println(Arrays.deepToString(stack.toArray()));
		}
		return stack.isEmpty();
	}
}
