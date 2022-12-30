public class PalindromicSubstrings {
    
    public int sol1(String s) {
        int n = s.length(),res = 0;
        boolean[][] dp = new boolean[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = true;
        }
        for(int L=2;L<=n;L++){
            for(int i=0;i+L<=n;i++){
                int j = i + L - 1;
                if(L==2){
                    dp[i][j] = s.charAt(i)==s.charAt(j);
                }
                else if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1]){
                    dp[i][j] = true;
                }
            }
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(dp[i][j]){
                    ans++;
                }
            }
        }
        return ans;
    }


    public int sol2(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); ++i) {
            ans += extendPalindromes(s, i, i);
            ans += extendPalindromes(s, i, i + 1);
        }

        return ans;
    }

    private int extendPalindromes(final String s, int l, int r) {
        int count = 0;

        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            ++count;
            --l;
            ++r;
        }

        return count;
    }
}
