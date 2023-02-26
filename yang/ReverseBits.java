public class ReverseBits {

    // 32-bit length
    // i는 0부터 31까지
    public int sol1(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }
        return n;
    }

    // x >> y
    // 정수 x의 각 비트를 y만큼 오른쪽으로 이동
    // 빈자리는 최상위 부호 비트와 가은 값으로 채워짐
    public int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;
        // XOR: 1 ^ 0 -> 1 or 0 ^ 1 -> 1
        // 다르니까
        if ((a ^ b) != 0) {
            // 1을 i, j에 위치시켜서 XOR 연산을 바꾸면 위치가 바뀐다.
            return n ^= (1 << i) | (1 << j);
        }
        return n;
    }

    /**
     * Bit manipulation
     * You need treat n as an unsigned value
     */
    public int sol2(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {

            if ((n >> i & 1) == 1) {
                ans |= 1 << 31 - i;
            }
        }

        return ans;
    }


    /**
     * Built-in
     */
    public int sol3(int n) {
        return Integer.reverse(n);
    }

    public static void main(String[] args) {

        ReverseBits t = new ReverseBits();

        int n = 2;
        System.out.println(t.sol1(n));
    }
}
