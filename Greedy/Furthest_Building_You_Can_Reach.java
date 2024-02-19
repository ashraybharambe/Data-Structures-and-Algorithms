import java.util.*;

public class Furthest_Building_You_Can_Reach {

    /*
        https://leetcode.com/problems/furthest-building-you-can-reach/
     */

    public static void main(String[] args) {
        int[] heights = new int[] {4,2,7,6,9,14,12};
        int bricks = 5;
        int totalLadderes = 1;
        int ans = furthestBuilding(heights, bricks, totalLadderes);
        // ans = 4
        System.out.println(ans);

    }

    public static int furthestBuilding(int[] heights, int bricks, int totalLadders) {
        PriorityQueue<Integer> ladders = new PriorityQueue<>();
        for (int i = 0; i + 1 < heights.length; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                if (ladders.size() < totalLadders) {
                    ladders.add(diff);
                } else {
                    if (!ladders.isEmpty() && ladders.peek() < diff) {
                        bricks -= ladders.poll();
                        ladders.add(diff);
                    } else {
                        bricks -= diff;
                    }
                    if (bricks < 0) {
                        return i;
                    }
                }
            }
        }
        return heights.length - 1;
    }
}
