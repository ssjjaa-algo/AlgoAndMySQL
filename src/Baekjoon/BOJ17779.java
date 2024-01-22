package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17779 {

    static int[][] map;
    static int N;
    static int[] area = new int[5];
    static int sum = 0;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {

                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {

                        if (isInvalid(r,c,d1,d2)) continue;

                        ans = Math.min(ans,divide(r,c,d1,d2));
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static int divide(int x, int y, int d1, int d2) {

        boolean[][] fiveBorder = new boolean[N + 1][N + 1];

        for (int i = 0; i <= d1; i++) {
            fiveBorder[x + i][y - i] = true;
            fiveBorder[x +d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            fiveBorder[x + i][y + i] = true;
            fiveBorder[x + d1 + i][y - d1 + i] = true;
        }

        return calculateArea(fiveBorder, x, y, d1, d2);
    }

    private static int calculateArea(boolean[][] fiveBorder, int x, int y, int d1, int d2) {

        Arrays.fill(area,0);
        int[][] copy = new int[N + 1][N + 1];

        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (fiveBorder[i][j]) break;
                area[0] += map[i][j];
                copy[i][j] = 1;
            }
        }

        for (int i = 1; i <= x + d2; i++) {
            for (int j = N; j > y; j--) {
                if (fiveBorder[i][j]) break;
                area[1] += map[i][j];
                copy[i][j] = 2;
            }
        }

        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (fiveBorder[i][j]) break;
                area[2] += map[i][j];
                copy[i][j] = 3;
            }
        }

        for (int i = x + d2 + 1; i <= N; i++) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (fiveBorder[i][j]) break;
                area[3] += map[i][j];
                copy[i][j] = 4;
            }
        }

        area[4] = sum;
        for (int i = 0; i <= 3; i++) {
            area[4] -= area[i];
        }

        Arrays.sort(area);

        return area[4] - area[0];

    }

    private static boolean isInvalid(int x, int y, int d1, int d2) {

        return x + d1 + d2 > N || y - d1 < 1 || y + d2 > N;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
                sum += map[i][j];
            }
        }


    }
}
