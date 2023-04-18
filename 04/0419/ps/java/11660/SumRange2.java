import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SumRange2 {
	public static void main(String args[]) throws IOException {
		BufferedReader bufferedReader = 
			new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = 
			new StringTokenizer(bufferedReader.readLine());
		int arr2D = Integer.parseInt(stringTokenizer.nextToken());
		int sumTrial = Integer.parseInt(stringTokenizer.nextToken());
		int[][] numArr = new int[arr2D + 1][arr2D + 1];
		long[][] sumArr = new long[arr2D + 1][arr2D + 1];

		for(int i = 1; i <= arr2D; i++)
		{
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for(int j = 1; j <= arr2D; j++)
			{
				numArr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				sumArr[i][j] = sumArr[i][j - 1] + sumArr[i - 1][j]
					- sumArr[i - 1][j - 1] + numArr[i][j];
			}
		}

		for(int i = 0; i < sumTrial; i++)
		{
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x1 = Integer.parseInt(stringTokenizer.nextToken());
			int y1 = Integer.parseInt(stringTokenizer.nextToken());
			int x2 = Integer.parseInt(stringTokenizer.nextToken());
			int y2 = Integer.parseInt(stringTokenizer.nextToken());
			long answer = sumArr[x2][y2] - (sumArr[x1 - 1][y2] + sumArr[x2][y1 - 1])
				+ sumArr[x1 - 1][y1 - 1];
			System.out.println(answer);
		}
	}
}
