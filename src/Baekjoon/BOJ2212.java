package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class BOJ2212 {

    static int N, K;
    static int[] map;
    static int[] dist;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        if (K >= N) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            dist[i] = map[i + 1] - map[i];
        }

        Arrays.sort(dist);

        int res = 0;
        for (int i = 0; i < N - K; i++) {
            res += dist[i];
        }

        System.out.println(res);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N];
        dist = new int[N - 1];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(map);
    }
}
