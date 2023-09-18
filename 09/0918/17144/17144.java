import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int EAST = 0;
    static final int WEST = 1;
    static final int SOUTH = 2;
    static final int NORTH = 3;

    static final int TOTALROOM = 4;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int simulationTime = Integer.parseInt(st.nextToken());
        map = new int[row + 1][col + 1];
        int[] circulator = new int[2]; // [0] = first row, [1] = second row

        for (int rowInput = 1; rowInput < row + 1; rowInput++) {
            int columnInput = 1;
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                map[rowInput][columnInput] = Integer.parseInt(st.nextToken());
                if (map[rowInput][columnInput] == -1 && circulator[0] == 0) {
                    circulator[0] = rowInput;
                } else if (map[rowInput][columnInput] == -1 && circulator[1] == 0) {
                    circulator[1] = rowInput;
                }
                columnInput++;
            }
        }

        for (int second = 0; second < simulationTime; second++) {
            boolean[][] isDusty = checkDust(row, col);
            spreadDust(isDusty, row, col);
            circulateUp(circulator, row, col);
            circulateDown(circulator, row, col);
        }

        int dustAmount = 0;
        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                dustAmount += map[i][j];
            }
        }
        dustAmount += 2; // circulator + 2
        System.out.println(dustAmount);
    }

    static public void circulateUp(int[] machine, int row, int col) {
        int i = machine[0];
        int j = 1;

        while(i != 1) {
            if(map[i - 1][1] != 0) {
                int dust = map[i - 1][1];
                map[i][1] = dust;
                map[i - 1][1] = 0;
            }
            i--;
        }
        while(j != col) {
            if(map[1][j + 1] != 0) {
                int dust = map[1][j + 1];
                map[1][j] = dust;
                map[1][j + 1] = 0;
            }
            j++;
        }
        i = 1;
        while(i != machine[0]) {
            if(map[i + 1][col] != 0) {
                int dust = map[i + 1][col];
                map[i][col] = dust;
                map[i + 1][col] = 0;
            }
            i++;
        }
        j = col;
        while(j != 2) {
            if(map[machine[0]][j - 1] != 0) {
                int dust = map[machine[0]][j - 1];
                map[machine[0]][j] = dust;
                map[machine[0]][j - 1] = 0;
            }
            j--;
        }

        map[machine[0]][1] = -1;
        map[machine[1]][1] = -1;
    }

    static public void circulateDown(int[] machine, int row, int col) {
        int i = machine[1];
        int j = 1;

        while(i != row) {
            if(map[i + 1][1] != 0) {
                int dust = map[i + 1][1];
                map[i][1] = dust;
                map[i + 1][1] = 0;
            }
            i++;
        }
        while(j != col) {
            if(map[row][j + 1] != 0) {
                int dust = map[row][j + 1];
                map[row][j] = dust;
                map[row][j + 1] = 0;
            }
            j++;
        }
        i = row;
        while(i != machine[1]) {
            if(map[i - 1][col] != 0) {
                int dust = map[i - 1][col];
                map[i][col] = dust;
                map[i - 1][col] = 0;
            }
            i--;
        }
        j = col;
        while(j != 2) {
            if(map[machine[1]][j - 1] != 0) {
                int dust = map[machine[1]][j - 1];
                map[machine[1]][j] = dust;
                map[machine[1]][j - 1] = 0;
            }
            j--;
        }

        map[machine[0]][1] = -1;
        map[machine[1]][1] = -1;
    }

    static public void spreadDust(boolean[][] isDusty, int row, int col) {
        int[][] copyMap = new int[row + 1][col + 1];

        for (int newR = 0; newR < row + 1; newR++) {
            for (int newC = 0; newC < col + 1; newC++) {
                copyMap[newR][newC] = map[newR][newC];
            }
        }

        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                if (isDusty[i][j]) {
                    int[] sides = checkAllSides(i, j);
                    int mapToSpread = sides[TOTALROOM];

                    if(sides[EAST] != -1) {
                        map[i][j + 1] += copyMap[i][j] / 5;
                    }
                    if(sides[WEST] != -1) {
                        map[i][j - 1] += copyMap[i][j] / 5;
                    }
                    if(sides[SOUTH] != -1) {
                        map[i + 1][j] += copyMap[i][j] / 5;
                    }
                    if(sides[NORTH] != -1) {
                        map[i - 1][j] += copyMap[i][j] / 5;
                    }

                    map[i][j] -= (copyMap[i][j] / 5) * mapToSpread;
                }
            }
        }
    }

    static public int[] checkAllSides(int row, int col) {
        int[] sides = new int[5]; //
        sides[TOTALROOM] = 4;

        if (col + 1 == map[row].length || map[row][col + 1] == -1) { // checkEast
            sides[EAST] = -1;
            sides[TOTALROOM]--;
        }
        if (col - 1 == 0 || map[row][col - 1] == -1) { // checkWest
            sides[WEST] = -1;
            sides[TOTALROOM]--;
        }
        if (row + 1 == map.length || map[row + 1][col] == -1) { // checkSouth
            sides[SOUTH] = -1;
            sides[TOTALROOM]--;
        }
        if (row - 1 == 0 || map[row - 1][col] == -1) { // checkNorth
            sides[NORTH] = -1;
            sides[TOTALROOM]--;
        }
        return sides;
    }

    static public boolean[][] checkDust(int row, int col) {
        boolean[][] isDusty = new boolean[row + 1][col + 1];

        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                if(map[i][j] != 0 && map[i][j] != -1) {
                    isDusty[i][j] = true;
                }
            }
        }
        return isDusty;
    }

}

