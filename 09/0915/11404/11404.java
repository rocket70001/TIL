import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int INFINITE = 999999999;
        int numCity = sc.nextInt();
        int numBus = sc.nextInt();
        int[][] distance = new int[numCity + 1][numCity + 1];

        for(int init = 1; init <= numCity; init++) {
            for (int initGo = 1; initGo <= numCity; initGo++) {
                if(init != initGo) {
                    distance[init][initGo] = INFINITE;
                }
            }
        }

        for(int route = 0; route < numBus; route++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            if(distance[from][to] > cost) {
                distance[from][to] = cost;
            }
        }

        for (int k = 1; k <= numCity; k++) {
            for (int s = 1; s <= numCity; s++) {
                for (int e = 1; e <= numCity; e++) {
                    distance[s][e] = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
                }
            }
        }

        for (int i = 1; i <= numCity; i++) {
            for (int j = 1; j <= numCity; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
