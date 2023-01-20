package we;

public class DecodeWays {
	public int numDecodings(String s) {
		// dp[i] : the total number which is able to make the ways
	    int[] dp = new int[s.length() + 1];
	    dp[0] = 1;
	    dp[1] = s.charAt(0) == '0' ? 0:1; // not allow to be the number starting with '0'
	    
	    for (int i = 2; i < dp.length; i++) {
	    	int one = Integer.valueOf(s.substring(i-1, i)); // when choosing one char
	    	int two = Integer.valueOf(s.substring(i-2, i)); // when choosing two chars
	    	
	    	// check if 1 ~ 9 when choosing one char 
	    	if (one >= 1) {
	    		dp[i] += dp[i-1];
	    	}
	    	// check if 10~26 when choosing two chars
	    	if (two >= 10 && two <= 26) {
	    		dp[i] =+ dp[i-2];
	    	}
	    }

	    return dp[s.length()];
	}
}


