package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1080 {

    static char[][] map1;
    static char[][] map2;
    static int N, M;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {

                if (map1[i][j] != map2[i][j]) {
                    cnt++;
                    change(i, j);
                }
            }
        }

        return !check() ? -1 : cnt;
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map1[i][j] != map2[i][j]) return false;
            }
        }

        return true;
    }

    private static void change(int row, int col) {

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (map1[i][j] == '1') map1[i][j] = '0';
                else map1[i][j] = '1';
            }
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map1 = new char[N][M];
        map2 = new char[N][M];

        for (int i = 0; i < N; i++) {
            map1[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            map2[i] = br.readLine().toCharArray();
        }
    }
}
