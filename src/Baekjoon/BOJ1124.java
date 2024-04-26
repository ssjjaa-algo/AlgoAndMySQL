package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1124 {

    static boolean[] primes = new boolean[100001];
    static int[] dp = new int[100001];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        findPrime();
        findUnderPrime(A, B);
    }

    private static void findUnderPrime(int A, int B) {

        int cnt = 0;
        for (int i = 2; i <= 100000; i++) {

            if (!primes[i]) continue;
            for (int j = 2; j <= 100000; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }

        for (int i = A; i <= B; i++) {
            if (!primes[dp[i]]) cnt++;
        }

        System.out.println(cnt);
    }

    private static void findPrime() {

        primes[1] = true;
        for (int i = 2; i <= 100000; i++) {
            if (!primes[i]) {
                dp[i] = 1;
                for (int j = 2; i * j <= 100000; j++) {
                    primes[i * j] = true;
                }
            }
        }
    }
}
