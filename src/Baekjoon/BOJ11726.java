package Baekjoon;

import java.io.*;

public class BOJ11726 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007 ;
        }

        System.out.println(dp[N]);

    }
}
