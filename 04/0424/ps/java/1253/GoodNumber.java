import java.util.Arrays;
import java.util.Scanner;

public class GoodNumber {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		long[] nums = new long[num];
		long numGoods = 0;
		
		for(int i = 0; i < num; i++)
			nums[i] = scanner.nextLong();
		Arrays.sort(nums);

		for(int i = 0; i < num; i++)
		{
			long goodOrBad = nums[i];
			int start = 0;
			int end = num - 1;

			while(start < end)
			{
				if(nums[start] + nums[end] == goodOrBad)
				{
					if(start == i)
						start++;
					else if(end == i)
						end--;
					else
						{
							numGoods++;
							break;
						}
				}
				else if(nums[start] + nums[end] < goodOrBad)
					start++;
				else
					end--;
			}
		}
		System.out.println(numGoods);
		scanner.close();
	}
} 
