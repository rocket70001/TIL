import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dna {
	static int[] acgt = new int[4];
	static int[] acgtCounting = new int[4];
	public static void main(String args[]) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sTokenizer = new StringTokenizer(bReader.readLine());
		int lenDna = Integer.parseInt(sTokenizer.nextToken());
		int lenPwd = Integer.parseInt(sTokenizer.nextToken());
		char[] strDna = new char[lenDna];
		int permutation = 0;

		strDna = bReader.readLine().toCharArray();

		sTokenizer = new StringTokenizer(bReader.readLine());
		for(int i = 0; i < 4; i++)
		{
			acgt[i] = Integer.parseInt(sTokenizer.nextToken());
			acgtCounting[i] = 0;
		}

		for(int i = 0; i < lenDna; i++)
		{
			addAcgt(strDna, i, acgtCounting);
			if(i > lenPwd - 1)
				removeAcgt(strDna, i - lenPwd, acgtCounting);
			if(i >= lenPwd - 1)
				permutation += checkAcgt(acgt, acgtCounting);

		}
		System.out.println(permutation);	
		bReader.close();	
	}

	private static void addAcgt(char[] strDna, int idx, int[] acgtCounting) {
		switch (strDna[idx]) {
			case 'A' :
				acgtCounting[0]++;
				break;
			case 'C' :
				acgtCounting[1]++;
				break;
			case 'G' :
				acgtCounting[2]++;
				break;
			case 'T' :
				acgtCounting[3]++;
				break;
		}
	}

	private static void removeAcgt(char[] strDna, int idx, int[] acgtCounting) {
		switch (strDna[idx]) {
			case 'A' :
				acgtCounting[0]--;
				break;
			case 'C' :
				acgtCounting[1]--;
				break;
			case 'G' :
				acgtCounting[2]--;
				break;
			case 'T' :
				acgtCounting[3]--;
				break;
		}
	}

	private static int checkAcgt(int[] acgt, int[] acgtCounting) {
		if(acgtCounting[0] < acgt[0])
			return 0;
		else if(acgtCounting[1] < acgt[1])
			return 0;
		else if(acgtCounting[2] < acgt[2])
			return 0;
		else if(acgtCounting[3] < acgt[3])
			return 0;
		else
			return 1;
	}
}
