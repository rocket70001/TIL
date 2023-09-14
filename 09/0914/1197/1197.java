public class Main {
    static ArrayList<Integer> connectedNodeNums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertices = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        List<Node> graph = new ArrayList();
        int cnt = 0;

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Node(fir, sec, weight));
        }

        Collections.sort(graph);
        connectedNodeNums = new ArrayList<>();
        for (int init = 0; init <= vertices; init++) {
            connectedNodeNums.add(init);
        }

        for(Node nodeToConnect : graph) {
            if(find(nodeToConnect.fir) == find(nodeToConnect.sec)) {
                continue;
            }
            cnt += nodeToConnect.weight;
            union(nodeToConnect);
        }
        System.out.println(cnt);
    }

    public static void union(Node node) {
        int fir = find(node.fir);
        int sec = find(node.sec);
        if (fir != sec) {
            if(node.fir < node.sec) {
                connectedNodeNums.set(sec, fir);
            } else {
                connectedNodeNums.set(fir, sec);
            }
        }
    }

    public static int find(int idx) {
        if (connectedNodeNums.get(idx) == idx) {
            return idx;
        }
        return find(connectedNodeNums.get(idx));
    }

    static class Node implements Comparable<Node> {
        int fir;
        int sec;
        int weight;

        public Node(int fir, int sec, int weight) {
            this.fir = fir;
            this.sec = sec;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node toCompare) {
           return this.weight - toCompare.weight;
        }
    }
}
