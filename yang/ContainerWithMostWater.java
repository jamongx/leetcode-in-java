public class ContainerWithMostWater {

   /**
     * LINEAR TIME SOLUTION
     * TC: O(1)
     * SC: O(1)
     */
    public int sol1(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        int max   = 0;
        int l  = 0;
        int r = height.length - 1;

        while (l < r) {
            // 물높이가 낮은 기둥에 맞춰지므로 Math.min
            int tmpMax = (r - l) * Math.min(height[l], height[r]);
            max = Math.max(max, tmpMax);
            if (height[l] < height[r]) {
                l++;
            }
            else {
                r--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

        ContainerWithMostWater t = new ContainerWithMostWater();
        System.out.println(t.sol1(height));
    }
}
