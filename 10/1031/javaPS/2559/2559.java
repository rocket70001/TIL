import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int day = Integer.parseInt(st.nextToken());
        int degreeRange = Integer.parseInt(st.nextToken());
        int[] degrees = new int[day];
        int[] sumDegree = new int[day];
        int maxDegree;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < day; i++) {
            degrees[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < degreeRange; i++) {
            sumDegree[0] += degrees[i];
        }

        maxDegree = sumDegree[0];
        for (int i = 0; i < day - degreeRange; i++) {
            sumDegree[i + 1] = sumDegree[i] - degrees[i] + degrees[degreeRange + i];
            maxDegree = Math.max(sumDegree[i + 1], maxDegree);
        }

        System.out.println(maxDegree);
    }
}

