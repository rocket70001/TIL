import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numV = Integer.parseInt(st.nextToken());
        int numE = Integer.parseInt(st.nextToken());
        int startV = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[numV + 1];
        boolean[] visited = new boolean[numV + 1];
        ArrayList ans = new ArrayList<>();

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
        dfs(graph, visited, startV, ans);
        for(int end = 0; end < ans.size(); end++) {
            sb.append(ans.get(end)).append('\n');
        }
        sb.append(0);
        System.out.println(sb);
    }

    static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int startV, ArrayList ans) {
        Stack<Integer> vert = new Stack<>();
        vert.push(startV);

        while(!vert.isEmpty()) {
            int nextVert = vert.pop();

            if(!visited[nextVert]) {
                visited[nextVert] = true;
                ans.add(nextVert);

                for(int neighbor : graph[nextVert]) {
                    if(!visited[neighbor]) {
                        vert.push(neighbor);
                    }
                }
            }
        }
    }
}
