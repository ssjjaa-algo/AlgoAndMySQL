package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12100 {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int res = 0;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        dfs(0);

        System.out.println(res);
    }

    private static void dfs(int cnt) {

        if (cnt == 5) {
            res = Math.max(res,calculate());
            return;
        }

        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            copyMap[i] = map[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            go(i);
            dfs(cnt + 1);
            for (int j = 0; j < n; j++) map[j] = copyMap[j].clone();
        }

    }

    private static void go(int direction) {

        for (int i = 0; i < n; i++)
            Arrays.fill(visited[i],false);

        if (direction == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    game(i,j,0);
                }
            }
        }

        if (direction == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    game(i,j,1);
                }
            }
        }

        if (direction == 2) {
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    game(i,j,2);
                }
            }
        }

        if (direction == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    game(i,j,3);
                }
            }
        }
    }

    private static void game(int x, int y, int direction) {

        if (map[x][y] == 0) return;

        while (true) {

            int nx = x + xDirection[direction];
            int ny = y + yDirection[direction];
            if(isInvalid(nx,ny) || visited[nx][ny]) return;

            if (map[nx][ny] == map[x][y]) {
                map[nx][ny] *= 2;
                map[x][y] = 0;
                visited[nx][ny] = true;
                return;
            }

            else if (map[nx][ny] != 0 && map[nx][ny] != map[x][y]) return;

            else {
                map[nx][ny] = map[x][y];
                map[x][y] = 0;
                x = nx;
                y = ny;
            }

        }
    }


    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= n || ny < 0 || ny >= n;
    }

    private static int calculate() {

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans,Arrays.stream(map[i]).max().orElseThrow());
        }

        return ans;
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        String[] input;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
    }
}
