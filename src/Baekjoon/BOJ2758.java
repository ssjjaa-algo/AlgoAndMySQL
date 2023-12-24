package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2758 {

    static int n,m,t;
    static long[][] dp = new long[11][2001];
    public static void main(String[] args) throws IOException {

        System.out.println(calculate());

    }

    private static String calculate() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int test = 0; test < t; test++) {
            input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);

            Arrays.fill(dp[0], 1L);

            for (int i = 1; i <= n; i++) {

                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
                }
            }

            sb.append(dp[n][m]).append("\n");
        }

        br.close();

        return sb.toString();
    }

}