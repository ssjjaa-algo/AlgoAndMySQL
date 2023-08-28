package Baekjoon;

import java.io.*;

public class BOJ17216 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException{

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int[] dp = new int[N];
        int res = 0;
        for (int i = 0 ; i < N; i++) {
            dp[i] = arr[i];
            for (int j = 0 ; j < i ; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            res = Math.max(res,dp[i]);
        }

        return res;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        N = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");

        arr = new int[N];
        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(input[N - (i + 1)]);
        }
    }
}