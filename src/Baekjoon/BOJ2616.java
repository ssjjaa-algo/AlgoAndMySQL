package Baekjoon;

import java.io.*;

public class BOJ2616 {
    static int[] arr;
    static int[][] dp;
    static int N, M;
    static int ans = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();
        prefixSum();
        calculate();

        System.out.println(dp[3][N]);
    }

    private static void calculate() {

        for (int i=1; i<4; i++) { // 소형 기관차의 수 만큼

            for (int j= i * M; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + arr[j] - arr[j-M]);
            }

        }
    }

    private static void prefixSum() {

        for (int i=1; i<=N; i++)
            arr[i] += arr[i-1];
    }
    private static void init() throws IOException{

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[4][N+1];

        String[] input = br.readLine().split(" ");
        for (int i = 1; i<= N; i++) {
            arr[i] = Integer.parseInt(input[i-1]);
        }
        M = Integer.parseInt(br.readLine());

    }

}
