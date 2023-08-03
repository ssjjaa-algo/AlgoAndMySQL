package Baekjoon;


import java.io.*;

public class BOJ2302 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] input;
    public static void main(String []args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N + 1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        int num;
        int res = 1;
        int start = 1;
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            num = Integer.parseInt(br.readLine());
            res = res * dp[num - start];
            start = num + 1;
        }

        res = res * dp[N - start + 1];

        System.out.println(res);
    }
}
