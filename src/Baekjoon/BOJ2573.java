package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ2573 {

    static class Node {
        int x, y, meltCnt;

        public Node(int x, int y, int meltCnt) {
            this.x = x;
            this.y = y;
            this.meltCnt = meltCnt;
        }
    }
    static int N,M;
    static int[][] map;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static List<Node> meltList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        while (true) {

            int divide = checkDivide();
            if (divide == 0) return 0;
            else if (divide >= 2) return cnt;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) continue;

                    melt(i,j);
                }
            }

            replaceIsland();
            cnt++;

        }

    }

    private static void replaceIsland() {

        for (Node node : meltList) {
            map[node.x][node.y] -= node.meltCnt;
            if (map[node.x][node.y] < 0) map[node.x][node.y] = 0;
        }
        meltList.clear();
    }

    private static int checkDivide() {

        int cnt = 0;

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;

                bfs(i,j,visited);
                cnt++;
            }
        }

        return cnt;
    }

    private static void bfs(int x, int y, boolean[][] visited) {

        Queue<int[]> q = new ArrayDeque<>();
        visited[x][y] = true;
        q.add(new int[]{x,y});

        while (!q.isEmpty()) {

            int[] temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = temp[0] + xDirection[i];
                int ny = temp[1] + yDirection[i];

                if (isInvalid(nx,ny) || visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx,ny});
            }
        }
    }

    private static void melt(int x, int y) {

        int nx, ny;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            nx = x + xDirection[i];
            ny = y + yDirection[i];

            if (isInvalid(nx,ny) || map[nx][ny] != 0) continue;

            cnt++;
        }

        meltList.add(new Node(x,y,cnt));
    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
    }
}
