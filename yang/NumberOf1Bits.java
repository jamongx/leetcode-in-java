public class NumberOf1Bits {

    // O(32) -> O(1)
    public int sol1(int n) {
        int count = 0;
        for (int i = 1; i < 33; i++) {
            if (getBit(n, i) == true) {
                count++;
            }
        }
        return count;
    }

    public boolean getBit(int n, int i) {
        return (n & (1 << i)) != 0;
    }   

    public int sol2(int n) {
        return Integer.bitCount(n);
    }

    // best performance
    public int sol3(int n) {

        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n >>> 1;
        }
        return count;
    }

    public static void main(String[] args) {

        NumberOf1Bits t = new NumberOf1Bits();

        int n = 2;
        System.out.println(t.sol3(n));
    }
}
