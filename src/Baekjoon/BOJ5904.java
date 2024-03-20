package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904 {

    static int[] dp = new int[31];
    static int N;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        System.out.println(recursive(N));

    }

    private static char recursive(int n) {

        int idx = 0;
        while (dp[idx] < n) {
            idx++;
        }

        if (n == 1) return 'm';
        if (n == 2 || n == 3 || dp[idx] == n) return 'o';

        if (n - dp[idx - 1] == 1) return 'm';
        else if (n > dp[idx - 1] && n - dp[idx - 1] <= idx + 3) return 'o';
        else return recursive(n - (idx + 3) - dp[idx - 1]);
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp[0] = 3;
        for (int i = 1; i <= 30; i++) {
            dp[i] = (dp[i - 1] * 2) + (i + 3);
        }
    }
}
