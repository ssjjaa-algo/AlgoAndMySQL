package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2146 {
    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int ans = Integer.MAX_VALUE;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int cnt = 0;
    static int[][] dp;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        N = Integer.parseInt(br.readLine());

        init();
        cntOfIsland();
        calculate();

        System.out.println(ans);

    }

    private static void calculate() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0) {
                    buildBridge(i,j,map[i][j]);
                }
            }
        }
    }

    private static void buildBridge(int x, int y, int numOfIsland) {
        Queue<Coordination> q = new ArrayDeque<>();
        q.add(new Coordination(x,y));
        dp[x][y] = 0; // 시작점은 다리길이 0부터 시작.
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Coordination temp = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = temp.x + xDirection[i];
                    int ny = temp.y + yDirection[i];

                    // 범위 안에 들어와있지 않거나, 해당 지점에 이미 더 짧은 다리가 있거나, 자신과 같은 수라면
                    if (!isIn(nx, ny) || dp[temp.x][temp.y] + 1 >= dp[nx][ny] || map[nx][ny] == numOfIsland) continue;


                    if (map[nx][ny] != 0) { // 섬이라면
                        ans = Math.min(ans, dp[temp.x][temp.y]);
                        return;
                    }

                    dp[nx][ny] = dp[temp.x][temp.y] + 1;
                    q.add(new Coordination(nx, ny));

                }
            }
        }
    }

    private static void init() throws IOException {
        String[] input;
        dp = new int[N][N];
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static void cntOfIsland() {
        visited = new boolean[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    ++cnt;
                    checkIsland(i,j);
                }
            }
        }
    }

    private static void checkIsland(int x, int y) {

        Queue<Coordination> q = new ArrayDeque<>();
        q.add(new Coordination(x,y));
        visited[x][y] = true;
        map[x][y] = cnt;

        while(!q.isEmpty()) {
            Coordination temp = q.poll();

            for (int i=0; i<4; i++) {
                int nx = temp.x + xDirection[i];
                int ny = temp.y + yDirection[i];

                if (!isIn(nx,ny) || visited[nx][ny] || map[nx][ny] != 1) continue;

                map[nx][ny] = cnt;
                q.add(new Coordination(nx,ny));
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;

        return true;
    }

}
