package Baekjoon;

import java.io.*;

public class BOJ1937 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dp;
    static int[][] arr;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    public static void main(String[] args) throws IOException{

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int ans = 0;
        for (int i = 0 ; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (dp[i][j] == 0) ans = Math.max(ans,dfs(i,j));
            }
        }
        return ans;
    }

    private static int dfs(int x, int y) {

        if (dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;

        for (int i = 0 ; i < 4; i++) {
            int nx = x + xDirection[i];
            int ny = y + yDirection[i];

            if (!isIn(nx,ny)) continue;

            if (arr[nx][ny] > arr[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
                // 다음 지점은 무조건 나보다 1칸 덜 먹기 때문에 다음 지점 + 1을 해줘야 한다.
            }
        }

        return dp[x][y];
    }
    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;

        return true;
    }
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] input;
        dp = new int[N][N];
        arr = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            input = br.readLine().split(" ");
            for (int j = 0 ; j < N; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }
    }
}
