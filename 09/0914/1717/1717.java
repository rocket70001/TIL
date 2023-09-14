import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int tmp = Integer.parseInt(st.nextToken());
        ArrayList<Integer> unionSet = new ArrayList<>(tmp);
        int numCalc = Integer.parseInt(st.nextToken());

        for (int init = 0; init <= tmp; init++) {
            unionSet.add(init);
        }

        for (int i = 0; i < numCalc; i++) {
            st = new StringTokenizer(br.readLine());
            int findOrUnion = Integer.parseInt(st.nextToken());
            int numFir = Integer.parseInt(st.nextToken());
            int numSec = Integer.parseInt(st.nextToken());
            if (findOrUnion == 0) {
                if (unionSet.get(numFir) >= unionSet.get(numSec)) {
                    unionSet.set(find(unionSet, numFir),find(unionSet, numSec));
                } else {
                    unionSet.set(find(unionSet, numSec), find(unionSet, numFir));
                }
            } else {
                if (find(unionSet, numFir) == find(unionSet, numSec)) {
                    sb.append("YES").append('\n');
                } else {
                    sb.append("NO").append('\n');
                }
            }
        }
        System.out.println(sb);
    }

    public static int find(ArrayList<Integer> unionSet, int numToFind) { // numToFind = 6
        if (numToFind == unionSet.get(numToFind)) {
            return numToFind;
        } else {
            unionSet.set(numToFind, unionSet.get(unionSet.get(numToFind)));
            return find(unionSet, unionSet.get(numToFind));
        }
    }
}
