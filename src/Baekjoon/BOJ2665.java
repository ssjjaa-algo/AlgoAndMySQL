package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2665 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static int n;
    static char[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        init();
        bfs();
        System.out.println(dp[n-1][n-1] == Integer.MAX_VALUE ? 0 : dp[n-1][n-1]);
    }

    private static void bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0)); // start point
        dp[0][0] = 0;

        int[] xDirection = {-1,0,1,0};
        int[] yDirection = {0,1,0,-1};

        while(!q.isEmpty()) {

            Node temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + xDirection[i];
                int ny = temp.y + yDirection[i];

                if (!isIn(nx,ny) || dp[temp.x][temp.y] >= dp[nx][ny]) continue;

                if (map[nx][ny] == '0') {

                    if (dp[temp.x][temp.y] + 1 < dp[nx][ny]) {
                        q.add(new Node(nx, ny));
                        dp[nx][ny] = dp[temp.x][temp.y] + 1;
                    }

                }

                else {

                    q.add(new Node(nx,ny));
                    dp[nx][ny] = dp[temp.x][temp.y];

                }

            }
        }

    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= n) return false;

        return true;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }
    }
}
