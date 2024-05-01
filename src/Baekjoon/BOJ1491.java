package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1491 {

    public static int[] spiralTraversal(int n, int m) {

        int[] xDirection = {1, 0, -1, 0};
        int[] yDirection = {0, 1, 0, -1};

        boolean[][] visited = new boolean[n][m];

        int step = 1;
        int x = 0;
        int y = 0;
        visited[x][y] = true;
        int direction = 0;

        while (step < n * m) {

            int nx = x + xDirection[direction];
            int ny = y + yDirection[direction];

            if (isInvalid(n, m, nx, ny) || visited[nx][ny]) {
                direction = (direction + 1) % 4;
                continue;
            }

            visited[nx][ny] = true;
            x = nx;
            y = ny;
            step++;
        }

        return new int[]{x, y};
    }

    private static boolean isInvalid(int n, int m, int nx, int ny) {

        return nx < 0 || nx >= n || ny < 0 || ny >= m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] result = spiralTraversal(N, M);
        System.out.println(result[0] + " " + result[1]);
    }
}
