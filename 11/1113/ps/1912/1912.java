import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[Integer.parseInt(st.nextToken())];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = getMax(arr);
        System.out.println(ans);
    }

    static public int getMax(int[] arr) {
        int sumArr = arr[0];
        int max = sumArr;

        for (int i = 1; i < arr.length; i++) {
            if(sumArr < 0 && arr[i] > sumArr) {
                sumArr = arr[i];
                max = arr[i];
                continue;
            }
            if(sumArr + arr[i] < 0) {
                sumArr = 0;
                continue;
            }
            sumArr += arr[i];
            max = Math.max(sumArr, max);
            max = Math.max(arr[i], max);
        }
        return max;
    }
}
