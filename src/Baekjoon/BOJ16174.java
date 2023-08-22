package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16174 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        init();
        System.out.println(bfs());
    }

    private static boolean check(int nx, int ny) {
        if (!isIn(nx,ny) || visited[nx][ny]) return false;

        return true;
    }
    private static boolean isIn(int nx, int ny) {

        if (nx < 0 || nx >= N || ny < 0 || ny >= N ) return false;

        return true;
    }

    private static String bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));

        while (!q.isEmpty()) {

            int size = q.size();
            int nx = 0;
            int ny = 0;
            for (int i = 0 ; i < size; i++) {
                Node temp = q.poll();

                if (temp.x == N - 1 && temp.y == N -1 ) return "HaruHaru";

                nx = temp.x + map[temp.x][temp.y];
                ny = temp.y;
                if (check(nx,ny)) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }

                nx = temp.x;
                ny = temp.y + map[temp.x][temp.y];
                if (check(nx,ny)) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }

            }
        }

        return "Hing";
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input;
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }


    }
}
