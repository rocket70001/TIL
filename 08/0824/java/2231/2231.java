import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int answer = 0;
        for(int i = num; i >= 0; i--) {
            int targetNum = i;
            int sum = 0;
            while(targetNum > 0) {
                sum += targetNum % 10;
                targetNum /= 10;
            }
            if(i + sum == num) {
                answer = i;
            }
        }
        System.out.println(answer);
    }
}
