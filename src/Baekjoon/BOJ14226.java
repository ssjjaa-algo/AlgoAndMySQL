package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14226 {

    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        // 클립보드에 1개가 복사되어 있는 채로 계속 만들 수 있다. 이 경우 이전 시간 + 1
        for (int i = 2; i <= N * 2; i++) {
            dp[i] = dp[i - 1] + 1;
        }

        for (int i = 2; i <= N * 2; i++) {
            // n개의 클립보드를 만드는데 걸리는 시간을 이용해서 n * k개는 k초의 시간이 더 걸린다.
            for (int j = 2; i * j <= N * 2; j++) {
                dp[i * j] = Math.min(dp[i * j], dp[i] + j);
            }

            // n + 1에서 1개 뺀 경우 1초의 시간
            for (int j = N * 2; j >= 2; j--) {
                dp[j] = Math.min(dp[j], dp[j + 1] + 1);
            }
        }

        System.out.println(dp[N]);

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N * 2 + 2];
        dp[1] = 1;

    }
}

