import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Qqq qqq = new Qqq(N);

        for(int i = 0; i < N; i++) {
            String order = br.readLine();
            switch(order.charAt(1)) {
                case 'u':
                    st = new StringTokenizer(order);
                    st.nextToken();
                    qqq.push(Integer.parseInt(st.nextToken()));
                    break;
                case 'o':
                    qqq.pop();
                    break;
                case 'i':
                    qqq.size();
                    break;
                case 'm':
                    qqq.isEmpty();
                    break;
                case 'r':
                    qqq.front();
                    break;
                case 'a':
                    qqq.back();
                    break;

            }
        }
        System.out.println(qqq.sb);
    }

    public static class Qqq {
        StringBuilder sb = new StringBuilder();
        int[] arr;
        int front;
        int rear;
        int size;

        Qqq(int N) {
            arr = new int[N];
            this.front = 0;
            this.rear = 0;
            this.size = 0;
        }
        void push(int data) {
           if(isFull()) {
               rear = 0;
               arr[rear] = data;
               size++;
           } else {
               rear++;
               arr[rear] = data;
               size++;
           }
        }

        void pop() {
            if(size == 0) {
                sb.append(-1).append('\n');
            } else {
                front++;
                size--;
                sb.append(arr[front]).append('\n');
            }
        }

        void size() {
            sb.append(size).append('\n');
        }

        void isEmpty() {
            if(size == 0) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        boolean isFull() {
            if(rear == arr.length - 1) {
                return true;
            } else {
                return false;
            }

        }

        void front() {
            if(size == 0) {
                sb.append(-1).append('\n');
            } else {
                sb.append(arr[front + 1]).append('\n');
            }
        }

        void back() {
            if(size == 0) {
                sb.append(-1).append('\n');
            } else {
                sb.append(arr[rear]).append('\n');
            }
        }
    }
}
