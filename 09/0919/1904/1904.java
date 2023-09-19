
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tiles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int moddingNumber = 15746;
        int number = Integer.parseInt(st.nextToken());
        tiles = new int[number + 1];
        tiles[0] = 1;
        tiles[1] = 2;

        makeTiles(number);
        System.out.println(tiles[number - 1]);
    }

    public static void makeTiles(int numberToMake) {
        for (int i = 2; i < numberToMake; i++) {
            tiles[i] = (tiles[i - 2] + tiles[i - 1]) % 15746;
        }
    }
}


