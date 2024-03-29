public class CountingBits {

    /**
     * Dynamic programming 
     * f(i) := i's# of 1's in bitmask
     * f(i) = f(i / 2) + (i % 2)
     * TC: O(n)
     * SC: O(n)
     */
    public int[] sol1(int n) {

        int[] result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // n >> 1 -> n/2
            result[i] = result[i >> 1] + (i & 1);
            System.out.println("result[" +i +"]=" +result[i] +" = " +"result[" +(i>>1) +"]=" +result[i >> 1] +" + " +"(i & 1)=" +(i & 1));
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

        int n = 5;
        System.out.println(t.sol1(n));
    }
}
