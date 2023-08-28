import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int boardSize = sc.nextInt();
        int[] queenCol = new int[boardSize];
        int[] ans = new int[1];

        putQueen(boardSize, queenCol, 0, ans);
        System.out.println(ans[0]);
    }

    static void putQueen(int boardSize, int[] queenCol, int row, int[] ans) {
        if(row == boardSize) {
            ans[0]++;
            return;
        }

        for(int col = 0; col < boardSize; col++) {
            if(isPossible(row, col, queenCol)) {
                queenCol[row] = col;
                putQueen(boardSize, queenCol, row + 1, ans);
            }
        }
    }

    static boolean isPossible(int row, int col, int[] queenCol) {
        for(int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = queenCol[prevRow];
            if(col == prevCol || Math.abs(row - prevRow) == Math.abs(col - prevCol)) {
                return false;
            }
        }
        return true;
    }
}
