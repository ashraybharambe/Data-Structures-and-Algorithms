public class Minimum_Cost_to_Merge_Stones {

    /*
        https://leetcode.com/problems/minimum-cost-to-merge-stones/description/
     */
    public static void main(String[] args) {
        int[] stones = new int[] {3,2,4,1};
        // ans = 20
        int ans = mergeStones(stones, 2);
        System.out.println(ans);
    }

    // Tabulation solution
    // Time complexity : O (N^3)
    // Space complexity : O (N^2)
    public static int mergeStones(int[] stones, int K) {
        int n = stones.length;
        // After each conversion K-1 stones remain and after performing m such operations only 1 stone should remain
        // N - m*(K - 1) = 1
        if (K > 1 && (n - 1) % (K - 1) != 0) return -1;

        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + stones[i];
        }
        if (K == 1) return presum[n];

        //dp[i][j] is the cost from i to j
        int[][] dp = new int[n][n];
        for (int len = K; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                // j - i + 1 == k  we merge it to one pile
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += presum[j + 1] - presum[i];
                }
            }
        }
        return dp[0][n - 1];
    }
}
