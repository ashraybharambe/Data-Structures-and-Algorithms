import java.util.*;

public class Rotate_Image_90 {
    /*
        https://leetcode.com/problems/rotate-image/description/
     */

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };

        rotate(matrix);
        printMatrix(matrix);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        while(top <= bottom && left <= right) {
            for(int i = left; i < right; i++) {
                Position p1 = new Position(top, i);
                Position p2 = new Position(top + (i - left), right);
                Position p3 = new Position(bottom, right - (i - left));
                Position p4 = new Position(bottom - (i - left),left);
                rotate(p1, p2, p3, p4, matrix);
            }
            top++;
            bottom--;
            left++;
            right--;
        }
    }

    private static void rotate (Position p1, Position p2, Position p3, Position p4, int[][] matrix) {
        Position[] pos = new Position[]{p1, p2, p3, p4};
        int prev = matrix[p4.row][p4.col];
        for(int i = 0 ;i < pos.length; i++) {
            int curr = matrix[pos[i].row][pos[i].col];
            matrix[pos[i].row][pos[i].col] = prev;
            prev = curr;
        }
    }

    private static class Position {
        int row;
        int col;

        public Position (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
