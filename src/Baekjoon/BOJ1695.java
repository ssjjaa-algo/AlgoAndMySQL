package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1695 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        int N =Integer.parseInt(br.readLine());

        arr = new int [N];
        dp = new int[N][N];
        String[] input = br.readLine().split(" ");
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        for (int i=0; i < N; i++) {
            Arrays.fill(dp[i],-1);
        }

        System.out.println(calculate(0, N-1));

    }

    private static int calculate(int left, int right) {

        if (left >= right) return 0;

        if (arr[left] == arr[right]) dp[left][right] = calculate(left + 1, right - 1);

        if (dp[left][right] != -1) return dp[left][right];

        dp[left][right] = Math.min(calculate(left + 1,right),calculate(left,right - 1)) + 1;

        return dp[left][right];
    }
}
