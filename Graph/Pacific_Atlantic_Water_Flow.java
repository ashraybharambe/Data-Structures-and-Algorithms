import java.util.*;
public class Pacific_Atlantic_Water_Flow {
    /*
        https://leetcode.com/problems/pacific-atlantic-water-flow/description/
     */
    public static void main(String[] args) {
        int[][] heights = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };

        List<List<Integer>> result = pacificAtlantic(heights);
        for(List<Integer> point : result) {
            System.out.println(point.get(0) + " " + point.get(1));
        }
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] pacific = new int[n][m];
        int[][] atlantic = new int[n][m];

        for(int i = 0; i < n; i++) {
            dfs(i, 0, pacific, heights);
            dfs(i, m-1, atlantic, heights);
        }

        for(int j = 0; j < m; j++) {
            dfs(0, j, pacific, heights);
            dfs(n - 1, j, atlantic, heights);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(pacific[i][j] == 1 && atlantic[i][j] == 1) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    public static void dfs (int row, int col, int[][] visit, int[][] heights) {
        int n = visit.length;
        int m = visit[0].length;
        if(row < 0 || row >= n || col < 0 || col >= m || visit[row][col] == 1) {
            return;
        }
        visit[row][col] = 1;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for(int i = 0; i < 4; i++) {
            int newx = row + dx[i];
            int newy = col + dy[i];
            if(isValid(newx, newy, visit) && heights[row][col] <=  heights[newx][newy]) {
                dfs(newx, newy, visit, heights);
            }
        }
    }

    public static boolean isValid (int x, int y, int[][] visit) {
        int n = visit.length;
        int m = visit[0].length;
        return x >= 0 && x < n && y >= 0 && y < m && visit[x][y] == 0;
    }
}
