import java.util.*;

public class Total_Appeal_of_A_String {
    /*
        https://leetcode.com/problems/total-appeal-of-a-string/description/
     */

    public static void main(String[] args) {
        String str = "abbca";
        long ans = appealSum(str);
        // ans = 28
        System.out.println(ans);
    }

    public static long appealSum(String str) {
        char[] s = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        long ans = 0;
        int n = s.length;
        long[] memo = new long[n];
        for (int i = 0; i < n; i++) {
            memo[i] = (i > 0) ? memo[i - 1] : 0;
            memo[i] += i - (map.getOrDefault(s[i], -1) == -1 ? 0 : map.get(s[i]) + 1);
            memo[i] += 1;
            ans += memo[i];
            map.put(s[i], i);
        }
        return ans;
    }
}
