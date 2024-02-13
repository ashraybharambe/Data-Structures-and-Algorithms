import java.util.*;

public class DeleteAndEarn {
    /*
        https://leetcode.com/problems/delete-and-earn/
     */
    public static void main(String[] args) {
        int[] nums = new int[] {2,2,3,3,3,4};
        int ans = deleteAndEarn(nums);
        //ans -> 9
        System.out.println(ans);
    }

    private static int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> distinctCounts = getDistinctCounts(nums);
        int[] arr = generateArray(distinctCounts);
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return solve(arr, 0, dp, distinctCounts);
    }

    private static HashMap<Integer, Integer> getDistinctCounts(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }

    private static int solve(int[] arr, int index, int[] dp, HashMap<Integer, Integer> counts) {
        if(index >= arr.length) {
            return 0;
        }
        if(dp[index] != -1) {
            return dp[index];
        }

        int ans1 = counts.get(arr[index]) * arr[index] +
                ((index+1 < arr.length && arr[index+1] != arr[index] + 1)
                        ? solve(arr, index + 1, dp, counts)
                        : solve(arr, index + 2, dp, counts));
        int ans2 = solve(arr, index + 1, dp, counts);
        dp[index]  = Math.max(ans1, ans2);
        return dp[index];
    }

    private static int[] generateArray(HashMap<Integer, Integer> distinctCounts) {
        int len = distinctCounts.size();
        int[] arr = new int[len];
        int index = 0;
        for(int num : distinctCounts.keySet()) {
            arr[index] = num;
            index++;
        }
        Arrays.sort(arr);
        return arr;
    }
}
