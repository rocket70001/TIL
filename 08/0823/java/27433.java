import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long sum = 1;
        System.out.println(factorial(num, sum));
    }
    public static Long factorial(long num, long sum) {
        if(num <= 1) {
            return sum;
        }
        sum *= num;
        --num;
        return factorial(num, sum);
    }
}
