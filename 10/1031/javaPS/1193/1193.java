import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int number = Integer.parseInt(st.nextToken());
        int[] val = new int[number];
        val[0] = 1;

        if(number == 1) {
            System.out.println("1/1");
            System.exit(0);
        }
        if(number == 2) {
            System.out.println("1/2");
            System.exit(0);
        }

        for (int i = 1; i < number; i++) {
            val[i] = val[i - 1] + i;
            if(val[i] > number) {
                if(i % 2 == 1) {
                    System.out.println((i - (number - val[i - 1])) + "/" + (1 + (number - val[i - 1])));
                } else {
                    System.out.println(1 + (number - val[i - 1]) + "/" + (i - (number - val[i - 1])));
                }
                break;
            }
        }
    }
}
