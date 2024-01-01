package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2294 {

    static int n,k;
    static int[] dp;
    static int[] coin;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int idx = 0;
        int num = 1;
        while (num <= k) {

            while (idx < n && num - coin[idx] > 0) {

                dp[num] = Math.min(dp[num], dp[num - coin[idx]] + dp[coin[idx]]);
                idx++;
            }

            num++;
            idx = 0;
        }

        return dp[k] >= 20000? -1 : dp[k];
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        dp = new int[100001];
        coin = new int[n];
        Arrays.fill(dp,20000);

        int num = 0;
        for (int i = 0; i < n; i++) {
            num = Integer.parseInt(br.readLine());
            dp[num] = 1;
            coin[i] = num;
        }

        Arrays.sort(coin);

    }
}
