import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numArr = Integer.parseInt(br.readLine());
        int[] arr = new int[numArr];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numArr; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftToEnd = new int[numArr];
        leftToEnd[0] = arr[0];
        int result = leftToEnd[0];
        for (int i = 1; i < numArr; i++) {
            leftToEnd[i] = Math.max(arr[i], leftToEnd[i - 1] + arr[i]);
            result = Math.max(result, leftToEnd[i]);
        }

        int[] rightToStart = new int[numArr];
        rightToStart[numArr - 1] = arr[numArr - 1];
        for (int i = numArr - 2; i >= 0; i--) {
            rightToStart[i] = Math.max(arr[i], rightToStart[i + 1] + arr[i]);
        }

        for (int i = 1; i < numArr - 1; i++) {
            int temp = leftToEnd[i - 1] + rightToStart[i + 1];
            result = Math.max(result, temp);
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
