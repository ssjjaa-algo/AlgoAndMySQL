package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1025 {
    static int[][] map;
    static int N, M;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        calculate(N, M);
    }

    private static void calculate(int N, int M) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dr = -N + 1; dr < N; dr++) {
                    for (int dc = -M + 1; dc < M; dc++) {

                        if (dr == 0 && dc == 0) continue;

                        find(i, j, dr, dc);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void find(int row, int col, int dr, int dc) {

        int num = 0;
        int idx = 1;
        while(!isInvalid(row, col)) {

            num += (map[row][col] * idx);
            if (isSqrtNum(num)) {
                ans = Math.max(ans, num);
            }

            row += dr;
            col += dc;
            idx *= 10;
        }
    }

    private static boolean isSqrtNum(int num) {

        int sqrt = (int)Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    private static boolean isInvalid(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

}
