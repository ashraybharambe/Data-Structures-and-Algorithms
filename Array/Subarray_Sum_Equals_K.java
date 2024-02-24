import java.util.*;

public class Subarray_Sum_Equals_K {
    /*
        https://leetcode.com/problems/subarray-sum-equals-k/description/
     */

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1};
        int k = 2;
        int ans = subarraySum(nums, k);
        // ans = 2
        System.out.println(ans);
    }

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> cumulativeSum = new HashMap<>();
        cumulativeSum.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++ ) {
            sum += nums[i];
            ans += cumulativeSum.getOrDefault(sum - k, 0);
            cumulativeSum.put(sum, cumulativeSum.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
