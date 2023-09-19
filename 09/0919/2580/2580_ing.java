import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[9][9];
        int[][] mapStatus = new int[9][9]; // 0 = none, 1 = original element

        for (int i = 0; i < 9; i++) {
            int col = 0;
            st = new StringTokenizer(br.readLine());

            while (col != 9) {
                map[i][col] = Integer.parseInt(st.nextToken());
                if (map[i][col] != 0) {
                    mapStatus[i][col] = 1;
                }
                col++;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map = setRow(map, mapStatus, i); 
            }
            if (i == 8 && !checkColumn(map, 0)) {
                printMap(map);
                i = 0;
            }
        }
        printMap(map);
    }

	// 해쉬셋으로 섞고 어레이리스트로 값 부여하려 했으나 값이 매번 동일한 문제
	// 값을 랜덤하게 넣어줘야 한다.
    public static int[][] setRow(int[][] sudoku, int[][] status, int row) {
        HashSet<Integer> emptyCol = new HashSet<>();
        HashSet<Integer> oneToNine = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            oneToNine.add(i);
        }

        for (int col = 0; col < 9; col++) {
            if (status[row][col] == 0) {
                emptyCol.add(col);
            } else {
                oneToNine.remove(sudoku[row][col]);
            }
        }

        ArrayList<Integer> leftNum = new ArrayList(oneToNine);
        ArrayList<Integer> leftCol = new ArrayList(emptyCol);
        while (!leftNum.isEmpty() && !leftCol.isEmpty()) {
            sudoku[row][leftCol.remove(leftCol.size() - 1)] = leftNum.remove(leftNum.size() - 1);
        }
        return sudoku;
    }

    public static boolean checkColumn(int[][] sudoku, int col) {
        if(col == 9) {
            return true;
        }

        Stack<Integer> numbers = new Stack<>();
        int[] oneToNine = new int[10];
        ArrayList<Integer> doubledIdx = new ArrayList();

        for (int i = 0; i < 9; i++) {
            numbers.push(sudoku[i][col]);
        }

        while (!numbers.isEmpty()) {
            int counting = numbers.pop();
            oneToNine[counting]++;
            if (oneToNine[counting] > 1) {
                return false;
            }
        }
        return checkColumn(sudoku, col + 1);
    }

    public static void printMap(int[][] sudoku) {
        System.out.println("===================================");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===================================");
    }
}

