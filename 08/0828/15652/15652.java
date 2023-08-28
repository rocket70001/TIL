import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    static int[] arr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    private static boolean checkOrder() {
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    private static void dfs(int n, int m, int d) {
        if(d == m && checkOrder()) {
            for(int a : arr) {
                sb.append(a + " ");
            }
            sb.append("\n");
            return;
        }
        if(d == m && !(checkOrder())) {
            return;
        }

        for(int i = 1; i <= n; i++) {
            arr[d] = i;
            dfs(n, m, d + 1);
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        arr = new int[m]; visit = new boolean[n+1];

        dfs(n, m, 0);
        System.out.print(sb);
    }

}
