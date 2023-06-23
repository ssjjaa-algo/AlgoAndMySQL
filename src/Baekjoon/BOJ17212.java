package Baekjoon;

import java.io.*;

public class BOJ17212 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[100010];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = 1;

        int min1, min2;
        for (int i= 8; i<= N; i++) {
            min1 = Math.min(dp[i-7],dp[i-5]) + 1;
            min2 = Math.min(dp[i-2],dp[i-1]) + 1;

            dp[i] = Math.min(min1,min2);
        }

        System.out.println(dp[N]);
    }


}
