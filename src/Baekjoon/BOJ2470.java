package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2470 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String[] input;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(arr);

        twoPointer(arr,N);

        System.out.println(sb.toString());
    }

    public static void twoPointer(int[] arr, int N) {

        int front = 0;
        int back = N - 1;

        int absMin = Integer.MAX_VALUE;
        int calculate = 0;

        int resFront = 0;
        int resBack = 0;

        while (front < back) {

            calculate = arr[front] + arr[back];

            if (absMin > Math.abs(calculate)) {
                absMin = Math.abs(calculate);
                resFront = arr[front];
                resBack = arr[back];
            }

            if (calculate == 0) break;

            if (calculate > 0) back--;

            else front++;
        }

        sb.append(resFront).append(" ").append(resBack);
        return;
    }
}
