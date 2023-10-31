import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] teamStats;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numTeamMember = Integer.parseInt(st.nextToken());
        teamStats = new int[numTeamMember + 1][numTeamMember + 1];

        for (int i = 1; i <= numTeamMember; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= numTeamMember; j++) {
                teamStats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeComb(1, 0, numTeamMember, new boolean[numTeamMember + 1]);
        System.out.println(minDiff);
    }

    public static void makeComb(int idx, int count, int numMember, boolean[] isStartTeam) {
        if(count == numMember / 2) {
            minDiff = Math.min(getDifference(numMember, isStartTeam), minDiff);
            if(minDiff == 0) {
                System.out.println(minDiff);
                System.exit(0);
            }
        }

        for (int i = idx; i <= numMember; i++) {
            if(!isStartTeam[i]) {
                isStartTeam[i] = true;
                makeComb(i + 1, count + 1, numMember, isStartTeam);
                isStartTeam[i] = false;
            }
        }
    }

    public static int getDifference(int numTeamMember, boolean[] isStartTeam) {
        int startSynergy = 0;
        int linkSynergy = 0;

        for (int i = 1; i <= numTeamMember; i++) {
            for (int j = i + 1; j <= numTeamMember; j++) {
                if(isStartTeam[i] && isStartTeam[j]) {
                    startSynergy += teamStats[i][j] + teamStats[j][i];
                } else if(!isStartTeam[i] && !isStartTeam[j]){
                    linkSynergy += teamStats[i][j] + teamStats[j][i];
                }
            }
        }
        return Math.abs(startSynergy - linkSynergy);
    }
}

