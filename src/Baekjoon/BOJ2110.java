package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2110 {
    static int N,C;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(binarySearch());
    }

    public static int binarySearch() {

        int left = 0;
        int right = arr[N -1] - arr[0];

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = calculate(mid);

            if (count >= C) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static int calculate(int mid) {

        int standard = arr[0];
        int count = 1;
        for (int i =1; i < N; i++) {
            if (arr[i] - standard >= mid) {
                count++;
                standard = arr[i];
            }
        }

        return count;
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

    }
}
