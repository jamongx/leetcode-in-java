package we;

public class PalindromicSubstrings {
	
	public static void main(String[] args) {
		PalindromicSubstrings test = new PalindromicSubstrings();
		test.countSubstrings("abcdefec");
	}
	
	public int countSubstrings(String s) {
		int ans = 0;

		for (int i = 0; i < s.length(); ++i) {
			// Palindromic의 사이즈가 홀수일때 
			ans += extendPalindromes(s, i, i);
			// Palindromic의 사이즈가 짝수일때
			ans += extendPalindromes(s, i, i + 1);
			
		}
		return ans;
	}

	private int extendPalindromes(final String s, int l, int r) {
		int count = 0;

		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			System.out.println(s.charAt(l) + "   "+s.charAt(r) + "##"+"left : "+l+"  "+"right"+r);
			
			++count;
			--l;
			++r;
			System.out.println("Count : "+count);
		}

		return count;
	}
}
