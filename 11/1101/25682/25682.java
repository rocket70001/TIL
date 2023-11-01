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
        int min = Integer.MAX_VALUE;

        // kk 보드의 최소 누적합 구하기
        System.out.println("black");
        test(boardRow,boardCol, blackBoard);
        System.out.println();
        System.out.println("white");
        test(boardRow,boardCol, whiteBoard);
        System.out.println();
        for (int i = 1; i + newBoardSize - 1 <= boardRow; i++) {
            for (int j = 1; j + newBoardSize - 1 <= boardCol; j++) {
                getSumPainting(newBoardSize, i, j, blackBoard);
            }
        }


        System.out.println(min);
    }

    public static void test(int row, int col, int[][] board) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
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
                if ((i + j) % 2 != 0 && boardToPaint[1][1] == boardToPaint[i][j]) {
                    boardToPaint[i][j] = 1;
                } else if ((i + j) % 2 == 0 && boardToPaint[1][1] != boardToPaint[i][j]) {
                    boardToPaint[i][j] = 1;
                }
            }
        }
        return boardToPaint;
    }

    public static int getSumPainting(int square, int startRow, int startCol, int[][] board) {
        int[][] sumPaintingTimes = new int[square + 1][square + 1];

        for (int i = 1; i <= square; i++) {
            for (int j = 1; j <= square; j++) {
                sumPaintingTimes[i][j] = sumPaintingTimes[i][j - 1] + sumPaintingTimes[i - 1][j]
                        - sumPaintingTimes[i - 1][j - 1] + board[startRow + i - 1][startCol + j - 1];
            }
        }

        System.out.println("sumPainting arr");
        test(square, square, sumPaintingTimes);
        System.out.println();

        return sumPaintingTimes[square][square];
    }
}

