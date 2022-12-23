public class DecodeWays {

    public int sol1(String s) {

        // 0은 알파벳을 만들수 없으므로 종료
        if (s.charAt(0) == '0') {
            return 0;
        }

        System.out.println("input = " + s);
        // A -> "1" ~ Z -> "26"
        // dp[i]는알파벳 조합의 개수를 저장한다.
        // dp[length+1]에는 최종값이 더해진다.
        int[] dp = new int[s.length() + 1];

        dp[0] = 1; // 첫번째 한자리 조합이 1이 나온다.
        dp[1] = 1; // 두번째 한자리 조합이 1이 나온다.

        for (int i = 1; i < s.length(); i++) {

            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);

            // curr 0이고 prev가 0이거나 prev가 3이상이면
            // 알파벳을 만들수 없으므로 프로그램을 종료
            if (curr == '0' && (prev == '0' || prev > '2')) {
                return 0;
            }

            // 2자리 숫자로 끊어지므로
            // prev 값을 기준으로 curr의 값에 따라 조합의 개수를 계산한다.
            switch (prev) {
                case '1':
                    // curr == '0'이면 한자리로서 의미가 없다.
                    // 그래서 dp[i-1] 값을 dp[i+1]로 넘겨준다.
                    if (curr == '0') {
                        dp[i + 1] = dp[i - 1];
                    }
                    // curr > '0'이면 한자리로서 의미가 있다.
                    // dp[i-1]과 dp[i]를 합쳐서 dp[i+1]
                    else {
                        dp[i + 1] = dp[i - 1] + dp[i];
                    }
                    break;
                case '2':
                    // 20는 이전 dp[i-1]의 값을 dp[i+1]로 던진다.
                    if (curr == '0') {
                        dp[i + 1] = dp[i - 1];
                    } 
                    // prev+curr -> 20 현재 문자열은 의미가 없다.
                    else if (curr <= '6') {
                        dp[i + 1] = dp[i - 1] + dp[i];
                    } 
                    else {
                        dp[i + 1] = dp[i];
                    }
                    break;
                case '0':
                default:
                    // prev == 0 또는 prev > 2 이면 curr가 어떤 값이라도 의미가 없다.
                    dp[i + 1] = dp[i];
                    break;
            }

            System.out.println(i +"-th loop, " +"(prev:" + prev + ") + (curr:" +curr +")");
            System.out.println("dp[" + (i - 1) + "] " + dp[i - 1] + ", dp[" + (i) + "] " + dp[i] + ", dp[" + (i + 1)
                    + "] " + dp[i + 1]);
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        DecodeWays t = new DecodeWays();

        String one = "1111";

        System.out.println(t.sol1(one));
    }    

}
