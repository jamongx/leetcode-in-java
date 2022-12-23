public class SumOfTwoIntegers {
    

    public int sol(int a, int b) {

        while(b != 0) {
            int tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {

        SumOfTwoIntegers t = new SumOfTwoIntegers();

        int a = 2;
        int b = 3;
        System.out.println(t.sol(a, b));
    }
}
