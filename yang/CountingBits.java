public class CountingBits {

    public int[] sol1(int n) {
        // Let f(i) := i's # of 1's in bitmask
        // F(i) = f(i / 2) + i % 2

        int[] result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }

    public int[] sol2(int n) {
        int[] result = new int[n + 1];

        int p = 1; // p tracks the index for number x
        int pow = 1;
        for (int i = 1; i <= n; i++) {
            if (i == pow) {
                result[i] = 1;
                pow <<= 1;
                p = 1;
            } else {
                result[i] = result[p] + 1;
                p++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        CountingBits t = new CountingBits();

        int n = 2;
        System.out.println(t.sol1(n));
    }
}
