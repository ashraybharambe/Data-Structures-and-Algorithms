import java.util.*;
public class Largest_Rectangle_in_Histogram {
    /*
        https://leetcode.com/problems/largest-rectangle-in-histogram/description/
     */

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int ans = largestRectangleArea(heights);
        System.out.println(ans);
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Pair> stack = new Stack<>();
        int right = 0;
        int maxArea = 0;
        for(right = 0; right < heights.length; right++) {
            int start = right;
            while(!stack.isEmpty() && stack.peek().height > heights[right]) {
                Pair top = stack.pop();
                int area = top.height * ( right - top.startIndex);
                maxArea = Math.max(area, maxArea);
                start = top.startIndex;
            }
            stack.push(new Pair(start, heights[right]));
        }

        while(!stack.isEmpty()) {
            Pair top = stack.pop();
            maxArea = Math.max(maxArea, top.height * (heights.length - top.startIndex));
        }
        return maxArea;
    }

    static class Pair{
        int startIndex;
        int height;

        public Pair(int index, int h) {
            this.startIndex = index;
            this.height = h;
        }
    }
}
