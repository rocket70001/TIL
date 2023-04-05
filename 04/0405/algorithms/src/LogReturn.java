import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

//N을 인수로 받아 Log2 N 이하의 가장 큰 정수를 리턴하는 static 메서드 lg() 구현하기

public class LogReturn {
	public static void main(String args[]){
		int N = StdIn.readInt();
		
		System.out.println(lg(N));
	}
	
	private static int lg(int N){
		int two = 1;
		int answer = 0;
		while(two <= N){
			two *= 2;
			answer++;
		}
		return --answer;
	}
}
