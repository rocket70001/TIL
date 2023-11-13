import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numTest = Integer.parseInt(st.nextToken());
        long[] pado = new long[110];

        for (int i = 0; i < 101; i++) {
            if(i <= 2) {
                pado[i] = 1;
            } else {
                pado[i] = pado[i - 3] + pado[i - 2];
            }
        }

        for (int i = 0; i < numTest; i++) {
            st = new StringTokenizer(br.readLine());
            int nextIdx = Integer.parseInt(st.nextToken()) - 1;
            System.out.println(pado[nextIdx]);
        }
    }
}
