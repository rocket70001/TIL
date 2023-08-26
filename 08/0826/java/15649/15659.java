import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] permu = new int[M];

        solve(permu, N);
    }

    static void solve(int[] permu, int N) {
        int idx = 1;
        for(int i = 1; i <= N; i++) {
            permu[0] = i;
            fillPermu(permu, N, idx);
        }
    }

    static void fillPermu(int[] permu, int N, int idx) {
        if(idx == permu.length) {
            printResult(permu);
        } else {
            for(int i = 1; i <= N; i++) {
                permu[idx] = i;
                for(int j = 0; j < idx; j++) {
                    if(permu[idx] == permu[j]) {
                        break;
                    }
                    if(j == idx - 1 && permu[idx] != permu[j]) {
                        fillPermu(permu, N, idx + 1);
                    }
                }
            }
        }
    }

    static void printResult(int[] permu) {
        for(int i = 0; i < permu.length; i++) {
            System.out.print(permu[i] + " ");
        }
        System.out.println();
    }
}
