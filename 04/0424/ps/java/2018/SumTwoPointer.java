import java.util.Scanner;

public class SumTwoPointer {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		int startI = 1;
		int endI = 1;
		int sum = 1;
		int permutation = 1;
		
		

		while(endI != num)
		{
			if(sum == num)
			{
				permutation++;
				endI++;
				sum += endI;
			}
			else if(sum < num)
				sum += ++endI;
			else if(sum > num)
				sum -= startI++;
		}
		System.out.println(permutation);
	}
}
