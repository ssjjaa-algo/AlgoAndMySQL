package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2096 {

    static int N;
    static int[][] originalMap;
    static int[][] copyMap;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        init();
        calculateMax();
        calculateMin();

        System.out.println(sb);
    }

    private static void calculateMax() {

        for (int i = 1; i < N; i++) {
            copyMap[i][0] = Math.max(copyMap[i - 1][0], copyMap[i - 1][1]) + copyMap[i][0];
            copyMap[i][1] = Math.max(Math.max(copyMap[i - 1][0], copyMap[i - 1][1]),copyMap[i-1][2]) + copyMap[i][1];
            copyMap[i][2] = Math.max(copyMap[i - 1][1], copyMap[i - 1][2]) + copyMap[i][2];
        }

        int max = Arrays.stream(copyMap[N-1]).max().orElseThrow();
        sb.append(max).append(" ");

    }

    private static void calculateMin() {

        copy();
        for (int i = 1; i < N; i++) {
            copyMap[i][0] = Math.min(copyMap[i - 1][0], copyMap[i - 1][1]) + copyMap[i][0];
            copyMap[i][1] = Math.min(Math.min(copyMap[i - 1][0], copyMap[i - 1][1]),copyMap[i-1][2]) + copyMap[i][1];
            copyMap[i][2] = Math.min(copyMap[i - 1][1], copyMap[i - 1][2]) + copyMap[i][2];
        }

        int min = Arrays.stream(copyMap[N-1]).min().orElseThrow();
        sb.append(min);
    }

    private static void copy() {

        for (int i = 0; i < N; i++) {
            copyMap[i] = originalMap[i].clone();
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        originalMap = new int[N][3];
        copyMap = new int[N][3];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                originalMap[i][j] = Integer.parseInt(input[j]);
                copyMap[i][j] = originalMap[i][j];
            }
        }
    }
}
