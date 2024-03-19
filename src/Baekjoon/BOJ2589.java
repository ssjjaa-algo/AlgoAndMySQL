package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ2589 {

    static int row, col;
    static char[][] map;
    static int[] xDirection = {-1, 0, 1, 0};
    static int[] yDirection = { 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (map[i][j] == 'W') continue;
                ans = Math.max(ans, bfs(i,j, new boolean[row][col]));
            }
        }
        System.out.println(ans - 1);
    }

    private static int bfs(int row, int col, boolean[][] visited) {

        visited[row][col] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row, col});

        int time = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] temp = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nr = temp[0] + xDirection[j];
                    int nc = temp[1] + yDirection[j];

                    if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == 'W') continue;

                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }

            time++;
        }

        return time;
    }

    private static boolean isInvalid(int nr, int nc) {
        return nr < 0 || nr >= row || nc < 0 || nc >= col;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        row = Integer.parseInt(input[0]);
        col = Integer.parseInt(input[1]);

        map = new char[row][col];

        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
        }


    }
}
