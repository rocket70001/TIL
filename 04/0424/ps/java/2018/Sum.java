import java.util.*;

public class Sum {
	public static void main(String[] agrs) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int[] sum = new int[num + 1];
		int permutation = 1;

		for(int i = 1; i <= num; i++)
			sum[i] = sum[i - 1] + i;
		for(int i = num / 2 + 1; i >= 0; i--)
		{
			if(num < 3)
				break;
			int j = 1;
			int sumRange = i;
			while(sumRange < num && sumRange > 0)
				sumRange += i - (j++);
			if(sumRange == num)
				permutation++;
			if(sum[i] < num)
				break;
		}

		System.out.println(permutation);
	}
}
