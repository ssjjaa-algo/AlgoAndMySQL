package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2194 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] input;
    static boolean[][] obstacles;
    static boolean[][] visited; // 실제 이동 가능한지 확인
    static int startX, startY, endX, endY;
    static int N,M,A,B;
    public static void main(String[] args) throws IOException{

        int K;
        input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        A = Integer.parseInt(input[2]);
        B = Integer.parseInt(input[3]);
        K = Integer.parseInt(input[4]);

        init(K);

        System.out.println(bfs(startX, startY,N,M));
    }

    private static int bfs(int startX, int startY,int N, int M) {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startX,startY));
        visited[startX][startY] = true;
        int cnt = 0;

        //상우하좌
        int[] xDirection = {-1,0,1,0};
        int[] yDirection = {0,1,0,-1};

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Node temp = q.poll();

                if (temp.x == endX && temp.y == endY) return cnt;

                for (int j = 0; j < 4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny) || visited[nx][ny]) continue;

                    if (ableToSwitch(nx,ny)) {
                        q.add(new Node(nx,ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    private static boolean ableToSwitch(int x, int y) {

        for (int i = x; i < x + A; i++) {

            for (int j = y; j < y + B; j++) {
                if (obstacles[i][j]) return false;
            }
        }

        return true;
    }

    private static boolean isIn(int nx, int ny) {

        if (nx < 1 || nx > N - A + 1|| ny < 1 || ny > M - B + 1) return false;

        return true;
    }
    private static void init(int K) throws IOException {

        obstacles = new boolean[N + 2][M + 2];
        visited = new boolean[N + 2][M + 2];

        int x, y;
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);

            obstacles[x][y] = true;
        }

        input = br.readLine().split(" ");
        startX = Integer.parseInt(input[0]);
        startY = Integer.parseInt(input[1]);

        input =  br.readLine().split(" ");
        endX = Integer.parseInt(input[0]);
        endY = Integer.parseInt(input[1]);
    }
}
