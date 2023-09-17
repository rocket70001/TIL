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
        /**
         * 7, 4, 0
         * 7, 3, 3
         * 8, 3, 2
         * 8, 4, 1 동쪽에서 전진할 때 북쪽으로 가지 않고 계속 동진
         * 8, 5, 1
         * 7, 5, 0
         * 6, 5, 0
         * 6, 4, 3
         *
         * backOrStop이 사용되지 않음
         */

        cleanRoom(room);
        System.out.println("robot's final location: " + robot[0] + " " + robot[1] + ", status -> " + robot[2]);
        System.out.println("robot's final back: " + (robot[0] - 1) + " " + robot[1] + ", status -> " + room[robot[0] - 1][robot[1]]);
        System.out.println(cntCleaning);
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
                }
            case 1:
                robot[2] = 0; // east to north
                if (roomToClean[row - 1][column] == 0) {
                    robot[0] = row - 1;
                    cleanRoom(roomToClean);
                    break;
                }
            case 2:
                robot[2] = 1; // south to east
                if (roomToClean[row][column + 1] == 0) {
                    robot[1] = column + 1;
                    cleanRoom(roomToClean);
                    break;
                }
            case 3:
                robot[2] = 2; // west to south
                if (roomToClean[row + 1][column] == 0) {
                    robot[0] = row + 1;
                    cleanRoom(roomToClean);
                    break;
                }
        }
//        System.out.println("last func -> goToClean");
    }

    public static void backOrStop(int[][] roomToClean) {
        int row = robot[0];
        int column = robot[1];

        System.out.println("backOR STOP WORKing");
        switch (robot[2]) {
            case 0: // back to south
                if (roomToClean[row + 1][column] == 1) {
                    System.out.println("end -> " + roomToClean[row + 1][column]);
                    break;
                } else {
                    robot[0] = row + 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 1: // back to west
                if (roomToClean[row][column - 1] == 1) {
                    System.out.println("end -> " + roomToClean[row][column - 1]);
                    break;
                } else {
                    robot[1] = column - 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 2: // back to north
                if (roomToClean[row - 1][column] == 1) {
                    System.out.println("end -> " + roomToClean[row - 1][column]);
                    break;
                } else {
                    robot[0] = row - 1;
                    cleanRoom(roomToClean);
                }
                break;
            case 3: // back to east
                if (roomToClean[row][column + 1] == 1) {
                    System.out.println("end -> " + roomToClean[row][column + 1]);
                    break;
                } else {
                    robot[1] = column + 1;
                    cleanRoom(roomToClean);
                }
        }
//        System.out.println("last func -> backOrStop");
    }

    public static void cleanRoom(int[][] roomToClean) {
        int row = robot[0];
        int column = robot[1];

        System.out.print(robot[0] + ", ");
        System.out.print(robot[1] + ", ");
        System.out.println(robot[2]);

        if (roomToClean[row][column] == 0) {
            if (roomToClean[row][column] == 0) {
                cntCleaning++;
                roomToClean[row][column] = 7; // '7' means it has been cleaned
            }
            if (roomToClean[row + 1][column] == 0 || roomToClean[row][column + 1] == 0
                    || roomToClean[row - 1][column] == 0 || roomToClean[row][column - 1] == 0) {
                goToClean(roomToClean);
            }
//            } else if (roomToClean[row + 1][column] == 7 || roomToClean[row][column + 1] == 7
//                    || roomToClean[row - 1][column] == 7 || roomToClean[row][column - 1] == 7) {
//                backOrStop(roomToClean);
//            }
            else{
                    backOrStop(roomToClean);
                }
            }
        else {
            backOrStop(roomToClean);
        }
//        System.out.println("last func -> cleanRoom");
        backOrStop(roomToClean);
        }
}
