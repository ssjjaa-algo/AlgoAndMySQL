package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class BOJ16935 {

    static int N,M,cnt;
    static int[][] map;
    static int[] command;
    public static void main(String[] args) throws IOException{

        init();
        calculate();
    }

    private static void calculate(){

        for (int i = 0; i < cnt; i++) {

            switch(command[i]) {
                case 1 : first();
                    break;

                case 2 : second();
                    break;

                case 3 : third();
                    break;

                case 4 : fourth();
                    break;

                case 5 : fifth();
                    break;
                case 6 : sixth();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void first() {

        int[][] temp = new int[N][M / 2];

        for (int i = 0; i < N / 2; i++) {
            temp[i] = map[i];

            map[i] = map[N - 1 - i];
            map[N - 1 - i] = temp[i];
        }
    }

    private static void second() {

        int temp;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp = map[i][j];
                map[i][j] = map[i][M - 1 - j];
                map[i][M - 1 - j] = temp;
            }
        }

    }

    private static void third() {

        // first : swap N, M
        int num = N;
        N = M;
        M = num;

        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[M - 1 - j][i];
            }
        }

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = temp[i];
        }

    }

    private static void fourth() {

        // first : swap N, M
        int num = N;
        N = M;
        M = num;

        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[j][N - 1 - i];
            }
        }

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = temp[i];
        }
    }

    private static void fifth() {

        int n = N / 2;
        int m = M / 2;
        int temp;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                temp = map[i][j];
                map[i][j] = map[i + n][j];
                map[i + n][j] = map[i + n][j + m];
                map[i + n][j + m] = map[i][j + m];
                map[i][j + m] = temp;
            }
        }
    }

    private static void sixth() {

        int n = N / 2;
        int m = M / 2;

        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp = map[i][j];
                map[i][j] = map[i][j + m];
                map[i][j + m] = map[i + n][j + m];
                map[i + n][j + m] = map[i + n][j];
                map[i + n][j] = temp;
            }
        }

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        cnt = Integer.parseInt(input[2]);

        map = new int[N][M];
        command = new int[cnt];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            map[i] = Arrays.stream(input)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        input =br.readLine().split(" ");
        for (int i = 0; i < cnt; i++) {
            command[i] = Integer.parseInt(input[i]);
        }
    }

}
