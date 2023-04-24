import java.util.*;

public class Jumong {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int numMaterial = scanner.nextInt();
		int materialNeeded = scanner.nextInt();
		int[] materials = new int[numMaterial];
		int sum;
		int combination = 0;

		for(int i = 0; i < numMaterial; i++)
			materials[i] = scanner.nextInt();

		Arrays.sort(materials);
		int startI = 0;
		int endI = numMaterial - 1;
		while(startI != endI)
		{
			if(materials[startI] + materials[endI] == materialNeeded)
			{
				combination++;
				startI++;
			}
			else if(materials[startI] + materials[endI] < materialNeeded)
				startI++;
			else if(materials[startI] + materials[endI] > materialNeeded)
				endI--;
		}
		System.out.println(combination);
	}
} 
