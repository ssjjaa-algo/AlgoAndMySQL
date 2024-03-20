package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16974 {

    static long[] patty;
    static int N;
    static long X;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(recursive(N, X));
    }

    private static long recursive(int n, long x) {

        if (n == 0) {
            if (x == 0) return 0;
            return 1;
        }
        if (x == 0 || x == 1) return 0;

        if (patty[n] < x) {
            return patty[n-1] + 1 + recursive(n - 1, x - patty[n]);
        }

        else if (patty[n] == x) {
            return patty[n - 1] + 1;
        }

        else return recursive(n - 1, x - 1);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        X = Long.parseLong(input[1]);

        patty = new long[N + 1];
        patty[0] = 1;

        for (int i = 1; i <= N; i++) {
            patty[i] = (patty[i - 1] * 2) + 1;
        }
    }
}
