import java.util.Scanner;

public class Average {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int times = sc.nextInt();
		int[] score = new int[times];
		int maxScore = 0;
		int sumScore = 0;

		for(int i = 0; i < times; i++)
		{
			score[i] = sc.nextInt();
			if(maxScore < score[i])
				maxScore = score[i];
			sumScore += score[i];
		}
		System.out.println((sumScore * 100.0) / maxScore / times);
	}
} 
