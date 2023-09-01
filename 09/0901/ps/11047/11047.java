import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int coinSort = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());
        int[] coins = new int[coinSort];

        for(int i = 0; i < coinSort; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }
        getOptCoin(coins, sum, 0);
    }

    public static void getOptCoin(int[] coins, int sum, int numberOfCoins) {
        if(sum == 0) {
            System.out.println(numberOfCoins);
            return;
        }
        for(int i = coins.length - 1; i >= 0; i--) {
            if(coins[i] <= sum) {
                numberOfCoins += sum / coins[i];
                sum = sum % coins[i];
                break;
            }
        }
        getOptCoin(coins, sum, numberOfCoins);
    }
}
