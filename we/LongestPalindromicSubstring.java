package we;

import java.util.Arrays;

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		if (s.isEmpty())
			return "";

		// [start, end] indices of the longest palindrome in s
		int[] indices = { 0, 0 };

		for (int i = 0; i < s.length(); ++i) {
			System.out.println("indices :"+Arrays.toString(indices));
			// 홀수일경우
			System.out.println("CharAt(i) :"+s.charAt(i)+"  "+"CharAt(i) :"+s.charAt(i));
			int[] indices1 = extend(s, i, i);
			if (indices1[1] - indices1[0] > indices[1] - indices[0])
				indices = indices1;
			System.out.println("indices1 :"+Arrays.toString(indices1));
			// 짝수일 경우
			if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
				System.out.println("CharAt(i) :"+s.charAt(i)+"  "+"CharAt(i+1) :"+s.charAt(i+1));
				int[] indices2 = extend(s, i, i + 1);
				if (indices2[1] - indices2[0] > indices[1] - indices[0])
					indices = indices2;
			
				System.out.println("indices2 :"+Arrays.toString(indices2));
			}
			System.out.println("-----------------------------------");
		}

		return s.substring(indices[0], indices[1] + 1);
	}

	// Returns [start, end] indices of the longest palindrome extended from s[i..j]
	private int[] extend(final String s, int i, int j) {
		for (; i >= 0 && j < s.length(); --i, ++j)
			if (s.charAt(i) != s.charAt(j))
				break;
		return new int[] { i + 1, j - 1 };
	}
}
