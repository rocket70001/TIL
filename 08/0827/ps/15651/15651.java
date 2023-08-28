import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int[] ascPermutation = new int[Integer.parseInt(st.nextToken())];

        for(int idx = 0; idx < ascPermutation.length; idx++) {
            ascPermutation[idx] = 1;
        }
        while(isEnd(ascPermutation, N) == false) {
            buildStr(sb, ascPermutation);
            ascPermutation[ascPermutation.length - 1]++;
            roundUpIfN(ascPermutation, N + 1);
        }
        buildStr(sb, ascPermutation);
        System.out.println(sb);
    }

    static void roundUpIfN(int[] arr, int N) {
        for(int i = arr.length - 1; i > 0; i--) {
            if(arr[i] == N) {
                arr[i] = 1;
                arr[i - 1]++;
            }
        }
    }

    static boolean isEnd(int[] arr, int N) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != N) {
                return false;
            }
        }
        return true;
    }

    static void buildStr(StringBuilder sb, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        sb.append('\n');
    }
}
