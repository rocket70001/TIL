import java.util.Scanner;

public class Sum {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int times = scanner.nextInt();
		scanner.nextLine();
		String nums = scanner.nextLine();
		int sum = 0;

		for(int i = 0; i < nums.length(); i++)
		{
			sum += Character.getNumericValue(nums.charAt(i));
		}
		System.out.println(sum);
	}
}

