package we;

public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
        System.out.println("######################");
        System.out.println("String s: "+s+"             t : "+t);
        System.out.println("######################");			
		int[] count = new int[128];
		int required = t.length();
		int bestLeft = -1;
		int minLength = s.length() + 1;

		for (final char c : t.toCharArray())
			++count[c];

		for (int l = 0, r = 0; r < s.length(); ++r) {
			if (--count[s.charAt(r)] >= 0)
				--required;
			
			System.out.println("CHAR(r) :"+s.charAt(r));
			while (required == 0) {
				
				
				if (r - l + 1 < minLength) {
					bestLeft = l;
					minLength = r - l + 1;
				}
				System.out.println("bestLeft : "+bestLeft);				
				System.out.println("minLength : "+minLength);
				System.out.println("Move Left Pointer to one right side");
				if (++count[s.charAt(l++)] > 0) {
					++required;
					System.out.println("Add one to required");						
				}
				System.out.println("right : "+r+"  "+"left : "+l+"  "+"required : "+required);
				System.out.println("---------------------");
			}
			System.out.println("right : "+r+"  "+"left : "+l+"  "+"required : "+required);
			System.out.println("=========================");
		}

		return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
	}
}
