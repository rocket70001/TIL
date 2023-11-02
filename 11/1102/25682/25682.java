import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int boardRow = Integer.parseInt(st.nextToken());
        int boardCol = Integer.parseInt(st.nextToken());
        int newBoardSize = Integer.parseInt(st.nextToken());
        char[][] board = makeBoard(boardRow, boardCol, br);
        int[][] whiteBoard = getPaintArea(boardRow, boardCol, board, 'W');
        int[][] blackBoard = getPaintArea(boardRow, boardCol, board, 'B');
        int[][] sumWhite = getSumPainting(boardRow, boardCol, whiteBoard);
        int[][] sumBlack = getSumPainting(boardRow, boardCol, blackBoard);
        int min = Integer.MAX_VALUE;

        for (int i = 0; i + newBoardSize <= boardRow; i++) {
            for (int j = 0; j + newBoardSize <= boardCol; j++) {
                int minWhite = getMinSum(sumWhite, i, j, newBoardSize);
                int minBlack = getMinSum(sumBlack, i, j, newBoardSize);
                int localMin = Math.min(minWhite, minBlack);
                min = Math.min(min, localMin);
            }
        }

        System.out.println(min);
    }

    public static char[][] makeBoard(int row, int col, BufferedReader br) throws IOException{
        char[][] board = new char[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            String line = br.readLine();
            for (int j = 1; j <= col; j++) {
                board[i][j] = line.charAt(j - 1);
            }
        }
        return board;
    }

    public static int[][] getPaintArea(int row, int col, char[][] board, char blackOrWhite) {
        int[][] boardToPaint = new int[row + 1][col + 1];
        if(blackOrWhite != board[1][1]) {
            boardToPaint[1][1] = 1;
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if ((i + j) % 2 != 0 && blackOrWhite == board[i][j]) {
                    boardToPaint[i][j] = 1;
                } else if ((i + j) % 2 == 0 && blackOrWhite != board[i][j]) {
                    boardToPaint[i][j] = 1;
                }
            }
        }
        return boardToPaint;
    }

    public static int[][] getSumPainting(int row, int col, int[][] board) {
        int[][] sumPaintingTimes = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                sumPaintingTimes[i][j] = sumPaintingTimes[i][j - 1] + sumPaintingTimes[i - 1][j]
                        - sumPaintingTimes[i - 1][j - 1] + board[i][j];
            }
        }

        return sumPaintingTimes;
    }

    public static int getMinSum(int[][] sumArr, int row, int col, int boardSize) {
        int sumRange;
        return sumRange = sumArr[boardSize + row][boardSize + col] - sumArr[row][boardSize + col]
                - sumArr[boardSize + row][col] + sumArr[row][col];
    }
}
