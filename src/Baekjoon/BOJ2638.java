package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2638 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N,M;
    static int[][] map;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static boolean[][] visited;
    static List<Node> deleteList = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        while(!check()) {

            bfs();
            deleteCheese();
            cnt++;
        }

        return cnt;
    }

    private static void deleteCheese() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 1) {
                    checkDelete(i,j);
                }
            }
        }

        for (Node node : deleteList) {
            map[node.x][node.y] = 0;
        }
        deleteList.clear();
    }

    private static void checkDelete(int x, int y) {

        int nx, ny;

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            nx = x + xDirection[i];
            ny = y + yDirection[i];

            if (map[nx][ny] == 2) cnt++;
        }

        if (cnt >= 2) deleteList.add(new Node(x,y));
    }

    private static void bfs() {

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i],false);
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        visited[0][0] = true;

        int nx,ny;

        while (!q.isEmpty()) {

            Node temp = q.poll();

            for (int i = 0; i < 4; i++) {
                nx = temp.x + xDirection[i];
                ny = temp.y + yDirection[i];

                if (!isIn(nx,ny) || visited[nx][ny] || map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                map[nx][ny] = 2;
                q.add(new Node(nx,ny));
            }
        }

    }

    private static boolean isIn(int nx, int ny) {

        return nx >= 0 && nx < N && ny >= 0 && ny < M;
    }
    private static boolean check() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

}
