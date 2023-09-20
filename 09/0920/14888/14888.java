import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] minAndMax = new int[2]; // [0] = min, [1] = max
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[Integer.parseInt(st.nextToken())];
        int[] numOperators = new int[4]; // [0] = plus, [1] = minus, [2] multiply, [3] divide;
        minAndMax[0] = Integer.MAX_VALUE;
        minAndMax[1] = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            numOperators[i] = Integer.parseInt(st.nextToken());
        }

        getAns(nums, numOperators, nums[0], 0);
        System.out.println(minAndMax[1]);
        System.out.println(minAndMax[0]);
    }

    public static void getAns(int[] numbers, int[] numOperators, int ans, int idx) {
        if(idx == numbers.length - 1) {
            minAndMax[0] = Math.min(minAndMax[0], ans);
            minAndMax[1] = Math.max(minAndMax[1], ans);
        }

        for (int operator = 0; operator < 4; operator++) {
            if(numOperators[operator] > 0) {
                numOperators[operator]--;

                switch (operator) {
                    case 0:
                        getAns(numbers, numOperators, ans + numbers[idx + 1], idx + 1);
                        break;
                    case 1:
                        getAns(numbers, numOperators, ans - numbers[idx + 1], idx + 1);
                        break;
                    case 2:
                        getAns(numbers, numOperators, ans * numbers[idx + 1], idx + 1);
                        break;
                    case 3:
                        getAns(numbers, numOperators, ans / numbers[idx + 1], idx + 1);
                        break;
                }
                numOperators[operator]++;
            }
        }
    }
}
