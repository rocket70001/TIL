import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            int col = 0;
            st = new StringTokenizer(br.readLine());

            while (col != 9) {
                map[i][col] = Integer.parseInt(st.nextToken());
                col++;
            }
        }

        if(solveSudoku(map)) {
            printMap(map);
        }
    }

    public static boolean solveSudoku(int[][] map) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(map[i][j] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if(isValid(map, i, j, number)) {
                            map[i][j] = number;
                            if (solveSudoku(map)) {
                                return true;
                            }
                        }
                        map[i][j] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[][] sudoku, int row, int col, int inputNumber) {
        for (int i = 0; i < 9; i++) {
            if(sudoku[i][col] == inputNumber || sudoku[row][i] == inputNumber
                    || sudoku[row - row % 3 + i / 3][col - col % 3 + i % 3] == inputNumber) {
                return false;
            }
        }
        return true;
    }

    public static void printMap(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}

