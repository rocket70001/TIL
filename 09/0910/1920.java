package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        getArr(sc, N, A);
        int M = sc.nextInt();
        int[] matchingNum = new int[M];
        getArr(sc, M, matchingNum);

        Arrays.sort(A);
        for(int i = 0; i < M; i++) {
            sb.append(searchBinary(A, matchingNum[i])).append('\n');
        }
        System.out.println(sb);
    }

    static void getArr(Scanner sc, int N, int[] arr) {
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    }

    static int searchBinary(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(key < arr[mid]) {
                high = mid - 1;
            } else if(key > arr[mid]) {
                low = mid + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

}
