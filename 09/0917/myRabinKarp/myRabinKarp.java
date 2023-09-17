import java.io.*;
import java.util.Scanner;

public class Main {
    static int ctn;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char[] pattern = sc.nextLine().toCharArray();
        char[] strToCompare = sc.nextLine().toCharArray();
        int patternVal = getHashVal(pattern, 0, pattern.length - 1);
        ctn = 0;

        for (int i = 0; i <= strToCompare.length - pattern.length; i++) {
            if(patternVal == getHashVal(strToCompare, i, i + pattern.length - 1)) {
                comparePattern(strToCompare, pattern, i);
            }
        }
        System.out.println("total ctn: " + ctn);
    }

    public static int getHashVal(char[] comparingStr, int from, int to) {
        int hashVal = 0;

        for (int idx = from; idx <= to; idx++) {
            if (comparingStr[idx] % 2 == 0) {
                hashVal *= (int) comparingStr[idx];
            } else {
                hashVal += (int) comparingStr[idx];
            }
        }
        return hashVal %= 17834;
    }

    public static void comparePattern(char[] comparingStr, char[] pattern, int startIdx) {
        int j = 0;
        for (int i = startIdx; i < startIdx + pattern.length; i++) {
            if(pattern[j] != comparingStr[i]) {
                break;
            } else if(i == startIdx + pattern.length - 1) {
                ctn++;
            } else {
                j++;
            }
        }
    }
}
