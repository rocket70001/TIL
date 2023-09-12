import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int cnt = 1;
    static void dfs(ArrayList<Integer>[] graph, int[] visited, int startV) {
        visited[startV] = cnt;

        for(int neighbor : graph[startV]) {
            if(visited[neighbor] == 0) {
                cnt++;
                dfs(graph, visited, neighbor);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numV = Integer.parseInt(st.nextToken());
        int numE = Integer.parseInt(st.nextToken());
        int startV = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[numV + 1];
        int[] visited = new int[numV + 1];

        for(int init = 0; init < numV + 1; init++) {
            graph[init] = new ArrayList<Integer>();
        }

        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int j = 1; j <= numV; j++) {
            Collections.sort(graph[j]);
        }
        dfs(graph, visited, startV);
        for(int end = 1; end < visited.length; end++) {
            System.out.println(visited[end]);
        }
    }
}
