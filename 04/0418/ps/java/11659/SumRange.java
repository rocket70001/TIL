import java.util.*;

public class SumRange {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int[] numArr = new int[number];
		int[] sumArr = new int[number];
		int sumTimes = sc.nextInt();

		for(int i = 0; i < number; i++)
			numArr[i] = sc.nextInt();
		sumArr[0] = numArr[0];
		for(int i = 1; i < number; i++)
			sumArr[i] = sumArr[i - 1] + numArr[i];
		for(int i = 0; i < sumTimes; i++)
		{
			int from = sc.nextInt();
			int to = sc.nextInt();
			if(from == 1)
				System.out.println(sumArr[to - 1]);
			else
				System.out.println(sumArr[to - 1] - sumArr[from - 2]);
		}
	}
} 
