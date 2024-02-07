package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2458 {

    static int N,M;
    static int[][] map;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || i == k || j == k) continue;

                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            int res = 0;
            for (int j = 1; j <= N; j++) {
                if (map[j][i] == 1) res++;
                if (map[i][j] == 1) res++;
            }

            ans += res == N - 1 ? 1 : 0;
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N + 1][N + 1];

        int a,b;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            map[a][b] = 1;
        }
    }
}
