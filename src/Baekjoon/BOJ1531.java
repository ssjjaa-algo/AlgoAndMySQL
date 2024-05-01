package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[][] coordinations = new int[N][4];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            coordinations[i][0] = Integer.parseInt(input[0]);
            coordinations[i][1] = Integer.parseInt(input[1]);
            coordinations[i][2] = Integer.parseInt(input[2]);
            coordinations[i][3] = Integer.parseInt(input[3]);

        }
        calculate(M, coordinations);
    }

    private static void calculate(int M, int[][] coordinations) {

        int[][] map = new int[101][101];

        for (int[] arr : coordinations) {

            for (int i = arr[0] ; i <= arr[2]; i++) {
                for (int j = arr[1]; j <= arr[3]; j++) {
                    map[i][j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (map[i][j] > M) ans++;
            }
        }

        System.out.println(ans);

    }
}
