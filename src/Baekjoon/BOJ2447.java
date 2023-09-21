package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class BOJ2447 {

    static char[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        init(N);

        recursive(0,0,N);
        calculate(N, bw);

        bw.close();
    }

    private static void calculate(int N, BufferedWriter bw) throws IOException {

        for (int i = 0; i < N; i++) {
            bw.write(map[i]);
            bw.write("\n");
        }

    }
    private static void init(int N) {

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i],' ');
        }
    }

    private static void recursive(int x, int y, int N) {

        if (N == 1) {
            map[x][y] = '*';
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0 ; j < 3; j++) {
                if (i == 1 && j == 1) continue;

                recursive(x + (N / 3) * i, y + (N / 3) * j, N / 3);
            }
        }
    }

}
