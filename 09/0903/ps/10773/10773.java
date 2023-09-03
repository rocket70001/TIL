import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Stack stack = new Stack(N);

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());
            if(num != 0) {
                stack.push(num);
            } else {
                stack.pop();
            }
        }
        System.out.println(stack.getSum());
    }

    public static class Stack {
        int[] arr;
        int top;
        StringBuilder sb = new StringBuilder();

        Stack(int N) {
            this.arr = new int[N];
            this.top = -1;
        }

        public void push(int data) {
            arr[++top] = data;
        }

        public void pop() {
            if(top == -1) {
                sb.append("-1").append('\n');
            } else {
                sb.append(arr[top--]).append('\n');
            }
        }

        public void getTop() {
            sb.append(top + 1).append('\n');
        }

        public void isEmpty() {
            if(top == -1) {
                sb.append("1").append('\n');
            } else {
                sb.append("0").append('\n');
            }
        }

        public void peek() {
            if(top == -1) {
                sb.append("-1").append('\n');
            } else {
                sb.append(arr[top]).append('\n');
            }
        }

        public int getSum() {
            int sum = 0;
            for(int i = top; i >= 0; i--) {
                sum += arr[i];
            }
            return sum;
        }
    }
}
