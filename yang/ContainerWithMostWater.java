public class ContainerWithMostWater {

    /**
     * Brute force
     * TC: O(n^2)
     * SC: O(1)
     */
    public int sol1(int[] height) {
        return 0;
    }

    /**
     * Two pointers / Greedy
     * TC: O(n)
     * SC: O(1)
     */
    public int sol2(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        int max = 0; // 최대 넓이
        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            // 물높이는 낮은 기둥에 맞춰지므로 Math.min
            // 낮은 높이 X 길이 (r-l)
            int tmpMax = (r - l) * Math.min(height[l], height[r]);
            max = Math.max(max, tmpMax);
            // l이 r보다 작으므로 더 큰 l을 찾아야 하므로 l++ 한다.
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater t = new ContainerWithMostWater();

        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

        System.out.println(t.sol1(height));
    }
}
