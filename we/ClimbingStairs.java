package we;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n <=2) return n;
        
        int prev1 = 1;
        int prev2 = 2;
        int cur= prev1 + prev2;
        
        while(n>=3) {
            cur = prev1 + prev2;
            prev1 = prev2;
            prev2 = cur;
            n -= 1;
        }
        return cur;
    }
}
