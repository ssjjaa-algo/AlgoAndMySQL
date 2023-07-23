package Baekjoon;

import java.io.*;

public class BOJ14503 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M;
    static int x,y,direction;
    static int[][] map;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(activeRobot());
    }

    private static int activeRobot() {

        int cnt = 0;
        boolean canCleanCnt;
        while(true) {
            if (map[x][y] == 0) {
                cnt++;
                map[x][y] = 2;
            }

            if (!findBlank()) {
                int reverse = (direction + 2) % 4;

                if(!isIn(x + xDirection[reverse], y + yDirection[reverse])) return cnt;
                if (map[x + xDirection[reverse]][y + yDirection[reverse]] == 1) return cnt;

                x = x + xDirection[reverse];
                y = y + yDirection[reverse];
                continue;
            }

            else changeDirection();
        }
    }
    private static void changeDirection() {
        direction--;
        if (direction == -1) direction = 3;

        if (map[x + xDirection[direction]][y + yDirection[direction]] == 0) {
            x = x + xDirection[direction];
            y = y + yDirection[direction];
        }
    }
    private static boolean findBlank() {
        int nx,ny;
        for (int i = 0; i < 4; i++) {
            nx = x + xDirection[i];
            ny = y + yDirection[i];

            if (!isIn(nx,ny)) continue;

            if (map[nx][ny] == 0) return true;
        }

        return false;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M ) return false;
        return true;
    }
    private static void init() throws IOException {
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        input = br.readLine().split(" ");
        x = Integer.parseInt(input[0]);
        y = Integer.parseInt(input[1]);
        direction = Integer.parseInt(input[2]);

        for (int i = 0; i < N ; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }


    }
}
