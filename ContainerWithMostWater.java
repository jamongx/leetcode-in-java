public class ContainerWithMostWater {

    // brute force
    public int sol1(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        return 0;
    }

    /*
     * LINEAR TIME SOLUTION
     * TC: O(n)
     * 
     */
    public int sol2(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        int max   = 0;
        int left  = 0;
        int right = height.length - 1;

        while (left < right) {
            // 물높이가 낮은 기둥에 맞춰지므로 Math.min
            int tmpMax = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, tmpMax);
            if (height[left] < height[right]) {
                left++;
            }
            else {
                right--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

        ContainerWithMostWater t = new ContainerWithMostWater();
        System.out.println(t.sol2(height));
    }
}
