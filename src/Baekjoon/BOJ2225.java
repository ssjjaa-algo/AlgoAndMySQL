package Baekjoon;

import java.io.*;
public class BOJ2225 {

    static int n,k;
    static int[][] dp;
    public static void main(String[] args) throws IOException{

        init();
        makeDpArray();
        System.out.println(dp[n][k]);
    }

    private static void makeDpArray() {

        for (int i = 2; i<= n; i++) {
            for (int j = 2; j <= k; j++) {

                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000;

            }
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int i = 1; i <= k; i++) {
            dp[1][i] = i;
        }

    }
}
