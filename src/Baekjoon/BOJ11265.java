package Baekjoon;

import java.io.*;

public class BOJ11265 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static String[] input;

    static long[][] map;
    public static void main(String[] args) throws IOException {

        init();
        Floyd();
        calculate();
    }

    private static void Floyd() {

        for (int k = 1; k <= N; k++) { // 거쳐가는 노드
            for (int i = 1; i <= N; i++) { // 출발 노드
                for (int j = 1; j <= N; j++) { // 도착 노드

                        if (i == j) continue;

                        if (map[i][k] + map[k][j] < map[i][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }
                }
            }
        }
    }

    private static void calculate() throws IOException {

        StringBuilder sb = new StringBuilder();

        int start, end, time;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");

            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);
            time = Integer.parseInt(input[2]);

            if (map[start][end] <= time) {
                sb.append("Enjoy other party").append("\n");
            }
            else {
                sb.append("Stay here").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new long[N+1][N+1];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    map[i][j] = 0;
                    continue;
                }

                map[i+1][j+1] = Long.parseLong(input[j]);
            }
        }

    }
}
