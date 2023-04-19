import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SumRemainder {
	public static void main(String args[]) throws IOException {
		BufferedReader bufferedReader 
			= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer 
			= new StringTokenizer(bufferedReader.readLine());
		int Ntimes = Integer.parseInt(stringTokenizer.nextToken());
		int divisor = Integer.parseInt(stringTokenizer.nextToken());
		long[] sumArr = new long[Ntimes];
		long[] sumRemainderArr = new long[divisor];
		long sumRange = 0;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		sumArr[0] = Long.parseLong(stringTokenizer.nextToken());
		for(int i = 1; i < Ntimes; i++)
			sumArr[i] = sumArr[i - 1] + Long.parseLong(stringTokenizer.nextToken());

		for(int i = 0; i < Ntimes; i++)
		{	
			int remainder = (int)(sumArr[i] % divisor);
			if(remainder == 0)
				sumRange++;
			sumRemainderArr[remainder]++;
		}

		for(int i = 0; i < divisor; i++)
			if(sumRemainderArr[i] > 1)
				sumRange += sumRemainderArr[i] * (sumRemainderArr[i] - 1) / 2;

		System.out.println(sumRange);
	}
}
