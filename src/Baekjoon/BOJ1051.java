package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1051 {

    static int N, M;
    static char[][] map;
    static int ans = 1;
    public static void main(String[] args) throws IOException {
        init();
        calculate();
    }

    private static void calculate() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                find(i, j, map[i][j], ans);
            }
        }

        System.out.println(ans * ans);
    }

    private static void find(int row, int col, char num, int next) {

        if (isInvalid(row + next, col + next)) return;

        if (map[row + next][col] == map[row + next][col + next] && map[row][col + next] == num && map[row + next][col] == num) {
            ans = Math.max(ans, next + 1);
        }
        find(row, col, num, next + 1);

    }

    private static boolean isInvalid(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
