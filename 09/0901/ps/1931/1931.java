import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numMeetings = Integer.parseInt(st.nextToken());
        long[][] meetingTimes = new long[numMeetings][2];
        int startIdx;
        int endIdx;
        long endTime = 0;
        long optSum = 0;

        for(int i = 0; i < numMeetings; i++) {
            st = new StringTokenizer(br.readLine());
            meetingTimes[i][0] = Long.parseLong(st.nextToken());
            meetingTimes[i][1] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(meetingTimes, Comparator.comparingLong(i -> i[1]));

        for(int k = 0; k < numMeetings - 1; k++) {
            if(meetingTimes[k][1] == meetingTimes[k + 1][1]) {
                startIdx = k;
                while(k < numMeetings - 1 && meetingTimes[k][1] == meetingTimes[k + 1][1]) {
                    k++;
                }
                endIdx = k;
                sortSection(meetingTimes, startIdx, endIdx);
            }
        }

        for(int j = 0; j < numMeetings; j++) {
            if(meetingTimes[j][0] >= endTime) {
                endTime = meetingTimes[j][1];
                optSum++;
            }
        }
        System.out.println(optSum);
    }

    public static void sortSection(long[][] arr, int start, int end) {
        for(int i = start; i < end; i++) {
            for(int j = start + 1; j <= end; j++) {
                if(arr[i][0] > arr[j][0]) {
                    long tmp = arr[i][0];
                    arr[i][0] = arr[j][0];
                    arr[j][0] = tmp;
                }
            }
        }
    }
}
