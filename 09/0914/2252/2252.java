public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int numStudent = Integer.parseInt(st.nextToken());
        int comparingTimes = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adjStudents = new ArrayList[numStudent + 1];
        int[] inDegree = new int[numStudent + 1];

        for(int init = 0; init < inDegree.length; init++) {
            adjStudents[init] = new ArrayList<>();
            inDegree[init] = 0;
        }

        for(int i = 0; i < comparingTimes; i++) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            adjStudents[fir].add(sec);
            inDegree[sec]++;
        }

        Queue<Integer> line = new LinkedList<>();

        for(int noFollower = 1; noFollower < inDegree.length; noFollower++) {
            if(inDegree[noFollower] == 0) {
                line.add(noFollower);
            }
        }

        while(!line.isEmpty()) {
            int curStudent = line.poll();
            sb.append(curStudent).append(' ');
            for(int frontMan : adjStudents[curStudent]) {
                inDegree[frontMan]--;
                if(inDegree[frontMan] == 0) {
                    line.add(frontMan);
                }
            }
        }
        System.out.println(sb);
    }
}
