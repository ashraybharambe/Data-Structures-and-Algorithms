import java.util.*;

public class DeleteAndEarn {
    public static void main(String[] args) {

    }

    public int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> distinctCounts = getDistinctCounts(nums);
        int[] arr = generateArray(distinctCounts);
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return solve(arr, 0, dp, distinctCounts);
    }

    private HashMap<Integer, Integer> getDistinctCounts(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }

    private int solve(int[] arr, int index, int[] dp, HashMap<Integer, Integer> counts) {
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

    private int[] generateArray(HashMap<Integer, Integer> distinctCounts) {
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
