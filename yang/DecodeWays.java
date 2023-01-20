public class DecodeWays {

    public int sol1(String s) {

        // 0은 알파벳을 만들수 없으므로 종료
        if (s.charAt(0) == '0') {
            return 0;
        }

        System.out.println("input = " + s);
        // A -> "1" ~ Z -> "26"
        // dp[i]는알파벳 조합의 개수를 저장한다.
        // dp[length+1]에는 최종값이 저장된다.
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

    public int sol2(String input) {
        // 문자열의 길이가 0 또는 첫번째 문자가 0
        if (input.length() == 1 || input.charAt(0) == '0') {
            return 0;
        }

        // input[0]
        int prev = 1; // input[1]을 계산할때 필요한 prev(input[0] 한자리 숫자 조합) 값이다.
        int curr = 1; // input[0] 한자리로 만들수 있는 조합수
        // int result = prev;
        int result = curr; // input[0] 한자리로 만들수 있는 조합수 (curr와 같다)

        for (int i = 1; i < input.length(); i++) {

            // i 문자가 0인데 i-1 문자가 1과 2가 아니면 return
            if (input.charAt(i) == '0' && input.charAt(i - 1) != '1' && input.charAt(i - 1) != '2') {
                return 0;
            }

            // i 문자가 0 이면
            if (input.charAt(i) == '0') {
                result = prev;
            }
            // (i-1)X10 + (i) < 27 이고 i-1이 '0'이 아니면
            else if ((input.charAt(i - 1) - '0') * 10 + (input.charAt(i) - '0') < 27 && input.charAt(i - 1) != '0') {
                result = prev + curr;
            }

            prev = curr;
            curr = result;
        }
        return result;
    }

    public static void main(String[] args) {
        DecodeWays t = new DecodeWays();

        String one = "12123";

        System.out.println(t.sol1(one));
        System.out.println(t.sol2(one));
    }    

}
