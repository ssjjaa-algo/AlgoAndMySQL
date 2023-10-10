package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1477 {

    static int N,M,L;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(binarySearch());
    }

    public static int binarySearch() {

        int left = 1;
        int right = L - 1;

        while(left <= right) {

            int mid = (left + right) / 2;

            if (calculate(mid) <= M) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int calculate(int mid) {

        int count = 0;
        for (int i = 0; i <= N; i++) {
            count += arr[i] / mid;
        }

        return count;
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);
        arr = new int[N + 2];

        input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(input[i - 1]);
        }
        arr[0] = 0;
        arr[N + 1] = L;
        Arrays.sort(arr);

        for (int i = 0; i <= N; i++) {
            arr[i] = arr[i + 1] - arr[i] - 1;
        }


    }
}
