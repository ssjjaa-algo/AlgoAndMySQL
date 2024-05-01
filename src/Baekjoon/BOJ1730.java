package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1730 {

    static class Node {
        boolean vertical, horizon;

        public Node(boolean vertical, boolean horizon) {
            this.vertical = vertical;
            this.horizon = horizon;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] commands = br.readLine().toCharArray();
        calculate(N, commands);
    }

    private static void calculate(int N, char[] commands) {

        Node[][] map = new Node[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Node(false, false);
            }
        }

        int r = 0;
        int c = 0;
        int[] rDirection = {-1, 0, 1, 0};
        int[] cDirection = {0, 1, 0, -1};
        int direction = 0;
        for (char cmd : commands) {

            if (cmd == 'U') direction = 0;
            else if ( cmd == 'R') direction = 1;
            else if (cmd == 'D') direction = 2;
            else direction = 3;

            int nr = r + rDirection[direction];
            int nc = c + cDirection[direction];
            if (isInvalid(nr, nc, N)) continue;

            if (direction == 0 || direction == 2) {
                map[r][c].vertical = true;
                map[nr][nc].vertical = true;
            }
            else {
                map[r][c].horizon = true;
                map[nr][nc].horizon = true;
            }
            r = nr;
            c = nc;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].horizon && map[i][j].vertical) sb.append('+');
                else if (map[i][j].horizon) sb.append('-');
                else if (map[i][j].vertical) sb.append('|');
                else sb.append('.');
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    private static boolean isInvalid(int nr, int nc, int N) {
        return nr < 0 || nr >= N || nc < 0 || nc >= N;
    }
}
