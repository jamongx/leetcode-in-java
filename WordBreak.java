import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class WordBreak {

    /**
     * Approach 1: Top-down w/ raw string
     * Map<String, boolean> memo -> 문자를 찾아보았다는 기록
     */
    public boolean sol1(String s, List<String> wordDict) {
        return sol1(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private boolean sol1(String s, Set<String> wordSet, Map<String, Boolean> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        // 문자열 s가 wordSet에 포함되어 있다면 return true;
        if (wordSet.contains(s)) {
            memo.put(s, true);
            return true;
        }

        // 1 <= prefix.length() < s.length()
        // loop를 돌면서 문자 s를 prefix, suffix 두 파트로 쪼갠다.
        // prefix가 wordset에 포함되어 있고
        // sol1(suffix)가 true -> return true;
        for (int i = 1; i < s.length(); i++) {

            String prefix = s.substring(0, i);
            String suffix = s.substring(i);

            if (wordSet.contains(prefix) && sol1(suffix, wordSet, memo)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }


    /**
     * Approach 2: Top-down w/ index
     */
    public boolean sol2(String s, List<String> wordDict) {
        return sol2(s, 0, new HashSet<>(wordDict), new Boolean[s.length()]);
    }

    // Returns true if s[i:] can be segmented
    private boolean sol2(String s, int i, Set<String> wordSet, Boolean[] memo) {
        if (i == s.length()) {
            return true;
        }
        if (memo[i] != null) {
            return memo[i];
        }

        for (int j = i + 1; j <= s.length(); j++) {
            if (wordSet.contains(s.substring(i, j)) && sol2(s, j, wordSet, memo)) {
                return memo[i] = true;
            }
        }

        return memo[i] = false;
    }


    /**
     * Approach 3: Bottom-up
     * Dynamic Programming
     */
    public boolean sol3(String s, List<String> wordDict) {
        int n = s.length();

        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i] := true if s[0..i) can be segmented
        boolean[] dp = new boolean[n + 1];

        // if dp[j] == true에 처음으로 걸리는 trigger
        dp[0] = true;

        // 단어의 시작(left), 끝(right), 
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {

                // s[0..j) can be segmented and s[j..i) in wordSet
                // So, s[0..i) can be segmented.
                System.out.println("dp[j:" +j +"]=" +dp[j] +", dp[j:" +i +"]=" +dp[i] +", s.substring(" +j +"," +i +")=" +s.substring(j, i));
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true; // No need to check further, move to next i
                    System.out.println("set dp[" +i +"]=true");
                    break;
                }
            }
            System.out.println("-----------------------");
        }

        // dictionary로 string이 쪼개지면 마지막도 true
        return dp[n];
    }


    /**
     * Approach 4: Bottom-up (optimized)
     * wordDict에서 max len word를 찾아서 그것보다 크면 skip한다.
     */
    public boolean sol4(String s, List<String> wordDict) {
        int n = s.length();
        int max = getMaxLength(wordDict);

        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i] := true if s[0..i) can be segmented
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // 단어의 시작(left), 끝(right), 
        for (int r = 1; r <= n; r++) {

            // left는 r-1 -> 0으로 (작은 것에서 큰것으로) 간다.
            for (int l = r - 1; l >= 0; l--) {
                if (r - l > max) {
                    break;
                }

                // s[0..j) can be segmented and s[j..i) in wordSet
                // So s[0..i) can be segmented
                if (dp[l] && wordSet.contains(s.substring(l, r))) {
                    dp[r] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    private int getMaxLength(List<String> wordDict) {
        return wordDict.stream().mapToInt(String::length).max().getAsInt();
    }


    public static void main(String[] args) {
        WordBreak t = new WordBreak();

        String s = "applepenapple";

        List<String> dict = new ArrayList<>();
        dict.add("apple");
        dict.add("pen");

        System.out.println(t.sol3(s, dict));
    }
}
