package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5557 {

    static int N;
    static int[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        dp[1][arr[0]] = 1;
        for (int i = 1; i < N - 1; i++) {

            for (int j = 0; j <= 20; j++) {

                if (dp[i][j] == 0) continue;

                if ( j - arr[i] >= 0) dp[i + 1][j - arr[i]] += dp[i][j];
                if ( j + arr[i] <= 20) dp[i + 1][j + arr[i]] += dp[i][j];
            }

        }

        System.out.println(dp[N - 1][arr[N - 1]]);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new long[N][21];

        String[] input = br.readLine().split(" ");

        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

    }
}
