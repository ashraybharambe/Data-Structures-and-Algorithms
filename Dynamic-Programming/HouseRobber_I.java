import java.util.Arrays;

public class HouseRobber_I {
    /*
        https://leetcode.com/problems/house-robber/description/
     */

    public static void main(String[] args) {
        int[] houses = {1,2,3,1};
        //ans -> 4
        int ans = rob(houses);
        System.out.println(ans);

        ans = solve(houses);
        System.out.println(ans);
    }

    private static int rob (int[] houses) {
        int[] memo = new int[houses.length];
        Arrays.fill(memo, -1);
        return solveRecursion(houses, 0, memo);
    }

    // solution using memoization
    private static int solveRecursion(int[] houses, int index, int[] memo) {
        if(index >= houses.length) {
            return 0;
        }
        if(memo[index] != -1) {
            return memo[index];
        }
        memo[index] = Math.max(houses[index] + solveRecursion(houses, index + 2, memo), solveRecursion(houses, index + 1, memo) );
        return memo[index];
    }

    // solution using tabulation
    private static int solve(int[] houses) {
        int len = houses.length;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++) {
            dp[i] = Math.max(
                    houses[i] + ((i - 2 >= 0) ? dp[i - 2] : 0), // option1 -> rob this house
                    (i - 1 >= 0) ? dp[i-1] : 0                    //option2 -> leave this house
            );
        }
        return dp[len - 1];
    }
}
