import java.io.*;
import java.util.Scanner;

public class Main {

    static int[] dpStatus;
    static int recurTestNum;
    static int dpTestNum;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        recurTestNum = 0;
        dpTestNum = 0;
        dpStatus = new int[num + 1];
        dpStatus[0] = 1;
        dpStatus[1] = 1;

        fiboRecur(num);
        fiboDP(num);

        System.out.println(recurTestNum + " " + dpTestNum);
    }

    public static int fiboRecur(int times) {
        if(times == 1 || times == 2) {
            recurTestNum++;
            return 1;
        } else {
            return fiboRecur(times - 1) + fiboRecur(times - 2);
        }
    }

    public static int fiboDP(int times) {
        if (times == 1 || times == 2) {
            return dpStatus[1];
        } else {
            for (int i = 2; i < times; i++) {
                dpTestNum++;
                dpStatus[i] = dpStatus[i - 1] + dpStatus[i - 2];
            }
        }
        return dpStatus[times];
    }
}
