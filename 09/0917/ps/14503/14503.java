import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int cntCleaning;
    static int width;
    static int length;

    static int[] robot;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        width = sc.nextInt();
        length = sc.nextInt();
        cntCleaning = 0;
        int[][] room = new int[width][length];
        robot = new int[3];
        robot[0] = sc.nextInt(); // robot's row
        robot[1] = sc.nextInt(); // robot's column
        robot[2] = sc.nextInt(); // where robot headed, 0 = north, 1 = east, 2 = south, 3 = west
        sc.nextLine();


        for (int row = 0; row < width; row++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int column = 0;

            while (st.hasMoreTokens()) {
                room[row][column] = Integer.parseInt(st.nextToken());
                column++;
            }
        }

        try {
            cleanRoom(room);
        } catch (StackOverflowError allRoomCleaned) {
            System.out.println(cntCleaning);
        }
    }

    public static void cleanRoom(int[][] roomToClean) {
        int row = robot[0];
        int column = robot[1];

        if (roomToClean[row][column] == 0) {
            cntCleaning++;
            roomToClean[row][column] = 7; // '7' means room cleaned
        }
        if (roomToClean[row + 1][column] == 0 || roomToClean[row][column + 1] == 0
                || roomToClean[row - 1][column] == 0 || roomToClean[row][column - 1] == 0) {
            goToClean(roomToClean);
        } else {
            backOrStop(roomToClean);
        }
}

    public static void goToClean(int[][] roomToClean) {
        int row = robot[0];
        int column = robot[1];

        switch (robot[2]) {
            case 0:
                robot[2] = 3; // north to west
                if (roomToClean[row][column - 1] == 0) {
                    robot[1] = column - 1;
                    cleanRoom(roomToClean);
                    break;
                } else {
                    goToClean(roomToClean);
                }
            case 1:
                robot[2] = 0; // east to north
                if (roomToClean[row - 1][column] == 0) {
                    robot[0] = row - 1;
                    cleanRoom(roomToClean);
                    break;
                } else {
                    goToClean(roomToClean);
                }
            case 2:
                robot[2] = 1; // south to east
                if (roomToClean[row][column + 1] == 0) {
                    robot[1] = column + 1;
                    cleanRoom(roomToClean);
                    break;
                } else {
                    goToClean(roomToClean);
                }
            case 3:
                robot[2] = 2; // west to south
                if (roomToClean[row + 1][column] == 0) {
                    robot[0] = row + 1;
                    cleanRoom(roomToClean);
                    break;
                } else {
                    goToClean(roomToClean);
                }
        }
    }

    public static void backOrStop(int[][] roomToClean) {
        int row = robot[0];
        int column = robot[1];

        switch (robot[2]) {
            case 0: // back to south
                if (roomToClean[row + 1][column] == 1) {
                    throw new StackOverflowError();
                } else {
                    robot[0] = row + 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 1: // back to west
                if (roomToClean[row][column - 1] == 1) {
                    throw new StackOverflowError();
                } else {
                    robot[1] = column - 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 2: // back to north
                if (roomToClean[row - 1][column] == 1) {
                    throw new StackOverflowError();
                } else {
                    robot[0] = row - 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 3: // back to east
                if (roomToClean[row][column + 1] == 1) {
                    throw new StackOverflowError();
                } else {
                    robot[1] = column + 1;
                    cleanRoom(roomToClean);
                }
        }
    }
}
