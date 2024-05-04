package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17829 {

    static int[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        System.out.println(divide(0, 0, N));
    }

    private static int divide(int row, int col, int N) {

        if (N == 2) {
            int[] arr = new int[4];
            int idx = 0;
            for (int i = row; i < row + 2; i++) {
                for (int j = col; j < col + 2; j++) {
                    arr[idx++] = map[i][j];
                }
            }
            Arrays.sort(arr);
            return arr[2];
        }
        else {
            int[] arr = new int[4];
            int size = N / 2;
            arr[0] = divide(row, col, size);
            arr[1] = divide(row, col + size, size);
            arr[2] = divide(row + size, col, size);
            arr[3] = divide(row + size, col + size, size);
            Arrays.sort(arr);
            return arr[2];
        }
    }
}
