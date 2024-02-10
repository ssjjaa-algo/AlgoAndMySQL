package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2217 {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int ans = 0;
        for (int i = 0; i < N; i++) {

            ans = Math.max(ans,Math.max(arr[i], arr[i] * (N - i)));
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
