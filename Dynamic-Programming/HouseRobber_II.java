import java.util.Arrays;

public class HouseRobber_II {
    /*
        https://leetcode.com/problems/house-robber-ii/description/
     */

    public static void main(String[] args) {
        int[] houses = new int[]{};
        int ans = rob(houses);
        System.out.println(ans);
    }
    /*
        We can re-use the soln of house_robber 1 by passing range of robbing houses, as
        if we rob 1st house we cannot rob last house vice-versa.
        IMPORTANT: consider 2 base case where len(houses) < 3
     */
    private static int rob (int[] houses) {
        if(houses.length == 1) {
            return houses[0];
        }
        if(houses.length == 2) {
            return Math.max(houses[0], houses[1]);
        }
        int[] dp1 = new int[houses.length];
        int[] dp2 = new int[houses.length];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);
        return Math.max(solve(houses, 0, houses.length - 2, dp1), solve(houses, 1, houses.length - 1, dp2));
    }

    private static int solve (int[] houses, int start, int end, int[] dp) {
        for(int i = start; i <= end; i++) {
            dp[i] = Math.max(
                    houses[i] + ((i - 2 >= 0) ? dp[i - 2] : 0),
                    (i - 1 >= 0) ? dp[i-1] : 0
            );
        }
        return dp[end];
    }

}
