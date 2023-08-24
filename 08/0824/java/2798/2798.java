import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCard = sc.nextInt();
        int blackjack = sc.nextInt();
        int sum = 0;
        int[] cards = new int[numCard];
        for(int i = 0; i < numCard; i++) {
            cards[i] = sc.nextInt();
        }
        for(int i = 0; i < numCard - 2; i++) {
            for(int j = i + 1; j < numCard - 1; j++) {
                for(int k = j + 1; k < numCard; k++) {
                    if(cards[i] + cards[j] + cards[k] <= blackjack
                            && cards[i] + cards[j] + cards[k] > sum) {
                        sum = cards[i] + cards[j] + cards[k];
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
