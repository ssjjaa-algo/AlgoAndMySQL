package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2448 {

    static int n;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        init();
        recursive(0, n-1, n);
        answer();
    }

    public static void answer() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < (2 * n) - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }


        System.out.println(sb);
    }

    public static void recursive(int row, int col, int n) {

        if (n == 3) {

            map[row][col] = '*';
            map[row + 1][col - 1] = map[row + 1][col + 1] = '*';

            for (int i = 0; i < 5; i++) {
                map[row + 2][col - 2 + i] = '*';
            }
            return;
        }

        int half = n / 2;
        recursive(row,col,half);
        recursive(row + half,col - half, half);
        recursive(row + half, col + half, half);

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][(2 * n )+ 1];

        for (int i = 0 ; i < n; i++) {
            Arrays.fill(map[i],' ');
        }

        br.close();
    }
}
