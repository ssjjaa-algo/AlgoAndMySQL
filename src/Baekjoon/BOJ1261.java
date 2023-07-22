package Baekjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1261 {

    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M;
    public static void main(String[] args) throws IOException {

        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        char[][] map = new char[N][M];
        int[][] dp = new int[N][M];

        String str;
        for (int i=0; i<N; i++) {
            str = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs(map,dp);

        System.out.println(dp[N-1][M-1]);
    }
    private static void bfs(char[][] map, int [][]dp) {

        Queue<Coordination> q = new ArrayDeque<>();
        q.add(new Coordination(0,0));
        dp[0][0] = 0;

        int[] xDirection = {-1,0,1,0};
        int[] yDirection = {0,1,0,-1};

        while (!q.isEmpty()) {

            int size = q.size();
            for (int i=0; i<size; i++) {
                Coordination temp = q.poll();

                for (int j=0; j<4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny)) continue;

                    if (map[nx][ny] == '1') {

                        if (dp[temp.x][temp.y] + 1 >= dp[nx][ny]) continue;

                        dp[nx][ny] = dp[temp.x][temp.y] + 1;
                        q.add(new Coordination(nx,ny));
                    }

                    else {

                        if (dp[temp.x][temp.y] >= dp[nx][ny]) continue;

                        dp[nx][ny] = dp[temp.x][temp.y];
                        q.add(new Coordination(nx,ny));
                    }
                }
            }
        }
    }
    private static boolean isIn(int nx, int ny) {

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return false;

        return true;
    }
}