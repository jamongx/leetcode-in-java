public class NumberOf1Bits {

    /**
     * Naive
     * TC: O(32) -> O(1) 
     * SC: O(1)
     */
    public int sol1(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            if (((n >> i) & 1) == 1) {
                ++count;
            }
        }
        return count;
    }
    

    /**
     * Best performance
     * n이 0일때 까지 loop를 돌기때문에 32번 보다 작게 돈다.
     * >>>: bit들을 오른쪽으로 이동시킨 후에 왼쪽의 빈공간은 모두 0으로 채운다.
     * TC: O(32) -> O(1) 
     * SC: O(1)
     */
    public int sol2(int n) {
        int count = 0;

        while (n != 0) {
            count += (n & 1);
            n = n >>> 1;
        }
        return count;
    }

    /**
     * Built-in
     * TC: O(1) 
     * SC: O(1)
     */
    public int sol3(int n) {
        return Integer.bitCount(n);
    }
    
    public static void main(String[] args) {
        NumberOf1Bits t = new NumberOf1Bits();

        int n = 2;

        System.out.println(t.sol1(n));
        System.out.println(t.sol2(n));
        System.out.println(t.sol3(n));
    }
}
