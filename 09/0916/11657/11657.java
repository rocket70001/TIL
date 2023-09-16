import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        final int INFINITE = Integer.MAX_VALUE;
        int numCity = sc.nextInt();
        int numRoute = sc.nextInt();
        Edge[] edges = new Edge[numRoute + 1];
        long [] shortCut = new long [numCity + 1];

        for (int init = 2; init < shortCut.length; init++) {
            shortCut[init] = INFINITE;
        }

        for (int route = 1; route <= numRoute; route++) {
            int cityA = sc.nextInt();
            int cityB = sc.nextInt();
            int time = sc.nextInt();
            edges[route] = new Edge(cityA, cityB, time);
        }

        for (int checkVertices = 1; checkVertices < numCity; checkVertices++) {
            for (int checkEdge = 1; checkEdge <= numRoute; checkEdge++) {
                int from = edges[checkEdge].from;
                int to = edges[checkEdge].to;
                int cost = edges[checkEdge].cost;

                if (shortCut[from] != INFINITE && shortCut[to] > shortCut[from] + cost) {
                    shortCut[to] = shortCut[from] + cost;
                }
            }
        }

        boolean cycle = false;
        for (int checkCycle = 1; checkCycle <= numRoute; checkCycle++) {
            int from = edges[checkCycle].from;
            int to = edges[checkCycle].to;
            int cost = edges[checkCycle].cost;

            if (shortCut[from] != INFINITE && shortCut[to] > shortCut[from] + cost) {
                System.out.println("-1");
                cycle = true;
                break;
            }
        }

        for (int i = 2; i <= numCity; i++) {
            if(cycle == true) break;
            if(shortCut[i] != INFINITE) {
                sb.append(shortCut[i]).append('\n');
            } else {
                sb.append("-1").append('\n');
            }
            if(i == numCity) {
                sb.delete(sb.length() - 1, sb.length());
                System.out.println(sb);
            }
        }
    }

    public static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
