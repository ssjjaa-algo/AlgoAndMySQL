package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5212 {

    static int R,C;
    static char[][] map;
    static char[][] resMap;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void answer(int minX, int maxX, int minY, int maxY) {

        StringBuilder sb = new StringBuilder();

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(resMap[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs() {

        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {

                    if (search(i,j)) {
                        resMap[i][j] = '.';
                    }
                    else {
                        minX = Math.min(minX, i);
                        maxX = Math.max(maxX, i);
                        minY = Math.min(minY, j);
                        maxY = Math.max(maxY, j);
                        resMap[i][j] = 'X';
                    }
                }
            }
        }

        answer(minX, maxX, minY, maxY);

    }

    private static boolean search(int x, int y) {

        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + xDirection[i];
            int ny = y + yDirection[i];

            if (!isIn(nx,ny)) {
                cnt++;
                continue;
            }

            if (map[nx][ny] == '.') cnt++;

        }

        return cnt >= 3 ? true : false;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= R || ny < 0 || ny >= C) return false;

        return true;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];
        resMap = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            resMap[i] = map[i].clone();
        }



    }
}
