import java.util.*;

public class Spiral_Matrix {
    /*
        https://leetcode.com/problems/spiral-matrix/description/
     */
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };

        List<Integer> order = spiralOrder(matrix);
        System.out.println(order);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = m - 1;
        List<Integer> result = new ArrayList<>();
        while (top <= bottom && left <= right) {
            traverseRight(left, right, top, bottom, matrix, result);
            top++;
            traverseDown(top, bottom, left, right, matrix, result);
            right--;
            traverseLeft(left, right, top, bottom, matrix, result);
            bottom--;
            traverseUp(top, bottom, left, right, matrix, result);
            left++;
        }
        return result;
    }

    private static void traverseRight(int left, int right, int top, int bottom, int[][] matrix, List<Integer> result) {
        while(left <= right && top <= bottom) {
            result.add(matrix[top][left]);
            left++;
        }
    }

    private static void traverseDown (int top, int bottom, int left, int right, int[][] matrix, List<Integer> result) {
        while(left <= right && top <= bottom) {
            result.add(matrix[top][right]);
            top++;
        }
    }

    private static void traverseLeft(int left, int right, int top, int bottom, int[][] matrix, List<Integer> result) {
        while(left <= right && top <= bottom) {
            result.add(matrix[bottom][right]);
            right--;
        }
    }

    private static void traverseUp (int top, int bottom, int left, int right, int[][] matrix, List<Integer> result) {
        while(left <= right && top <= bottom) {
            result.add(matrix[bottom][left]);
            bottom--;
        }
    }


}
