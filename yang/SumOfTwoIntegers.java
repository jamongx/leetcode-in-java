public class SumOfTwoIntegers {

    public int sol1(int a, int b) {

        while (b != 0) {
            int tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }
        return a;
    }

    public int sol2(int a, int b) {

        while (b != 0) { // Still have carry bits
            int carry = a & b; // Record carry bits
            a ^= b; // ^ works like + w/o handling carry bits
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        SumOfTwoIntegers t = new SumOfTwoIntegers();

        int a = 2;
        int b = 3;

        System.out.println(t.sol1(a, b));
        System.out.println(t.sol2(a, b));
    }
}
