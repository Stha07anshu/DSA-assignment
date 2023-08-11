public class MaxPointsByHittingTargets {
    public static int maxPoints(int[] a) {
        int n = a.length;
        
        // Create an array with two extra elements at the beginning and end for padding
        int[] paddedTargets = new int[n + 2];
        paddedTargets[0] = paddedTargets[n + 1] = 1; // Padding with value 1
        System.arraycopy(a, 0, paddedTargets, 1, n); // Copy original targets into padded array
        
        // Create a 2D array for dynamic programming
        int[][] dp = new int[n + 2][n + 2];

        // Loop for different subranges
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int i = left; i <= right; i++) {
                    // Update dp[left][right] with maximum points considering all possible hit positions
                    dp[left][right] = Math.max(
                        dp[left][right],
                        dp[left][i - 1] +                    // Points from the left side
                        paddedTargets[left - 1] *            // Left padding
                        paddedTargets[i] *                  // Points from hitting the current target
                        paddedTargets[right + 1] +           // Right padding
                        dp[i + 1][right]                     // Points from the right side
                    );
                }
            }
        }

        return dp[1][n]; // Return maximum points for the entire range
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 5, 8};
        int result = maxPoints(a);
        System.out.println("Maximum points: " + result);//output=167
    }
}
