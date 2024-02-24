import java.util.*;

public class Container_With_Most_Water {

    /*
        https://leetcode.com/problems/container-with-most-water/
     */
    public static void main(String[] args) {
        int[] height = new int[] {1,8,6,2,5,4,8,3,7};
        int ans = maxArea(height);
        // ans -> 49
        System.out.println(ans);
    }

    public static int maxArea(int[] height) {
        int n = height.length;
        int area = 0;
        int i=0,j=n-1;
        while(i < j){
            area = Math.max(area,((j-i)*Math.min(height[i],height[j])));
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return area;
    }
}
