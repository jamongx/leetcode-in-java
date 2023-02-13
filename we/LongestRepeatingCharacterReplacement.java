package we;

public class LongestRepeatingCharacterReplacement {
	public int characterReplacement(String s, int k) {
        System.out.println("######################");
        System.out.println("String s : "+s+"             k : "+k);
        System.out.println("######################");		
		int ans = 0;
		int maxCount = 0;
		int[] count = new int[128];
		// AABABBA
		for (int l = 0, r = 0; r < s.length(); ++r) {
			System.out.println("CHAR : "+s.charAt(r));
			maxCount = Math.max(maxCount, ++count[s.charAt(r)]);
			System.out.println("MAX Count : "+maxCount);
			// maxCount + k가 r-l+1 보다 작을때 left포인트를 한칸 오른쪽으로 이동 
			while (maxCount + k < r - l + 1) {
				--count[s.charAt(l++)];
				System.out.println("Move Left Pointer to one right side");
			}
			System.out.println("ANS : "+ans +"    (r-l+1) :"+(r-l+1));
			ans = Math.max(ans, r - l + 1);
			System.out.println("ANS : "+ans);
			System.out.println("----------------------------");
		}

		return ans;
	}
}
