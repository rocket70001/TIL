import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dpStatus;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dpStatus = new int[200][200][200];
        int fir = 0, sec = 0, thd = 0;

        while(true) {
            st = new StringTokenizer(br.readLine());
            fir = Integer.parseInt(st.nextToken());
            sec = Integer.parseInt(st.nextToken());
            thd = Integer.parseInt(st.nextToken());

            if(fir == -1 && sec == -1 && thd == -1) break;
            int result = w(fir, sec, thd);
            writeResult(fir, sec, thd, result);
        }

        System.out.println(sb);
    }

    public static int w(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (dpStatus[a][b][c] != 0) {
            return dpStatus[a][b][c];
        }
         else if (a > 20 || b > 20 || c > 20) {
            return dpStatus[a][b][c] = w(20, 20, 20);
        } else if (a < b && b < c) {
            return dpStatus[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        } else {
           return dpStatus[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        }
    }

    public static void writeResult(int a, int b, int c, int answer) {
            sb.append("w(").append(a).append(", ").append(b).append(", ")
                    .append(c).append(") = ").append(answer).append('\n');
    }
}
