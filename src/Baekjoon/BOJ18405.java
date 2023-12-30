package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ18405 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static int N,K,S,X,Y;
    static int[][] map;
    static Queue<Node> viruses[];
    public static void main(String[] args) throws IOException {

        init();
        bfs();
        System.out.println(map[X - 1][Y - 1]);
    }

    private static void bfs() {

        int size = 0;

        while (S > 0) {

            for (int i = 1; i <= K; i++) {
                size = viruses[i].size();
                Node temp = null;

                for (int j = 0; j < size; j++) {
                    temp = viruses[i].poll();
                    int nx,ny = 0;

                    for (int k = 0; k < 4; k++) {
                        nx = temp.x + xDirection[k];
                        ny = temp.y + yDirection[k];

                        if (isInvalid(nx,ny) || map[nx][ny] != 0) continue;

                        map[nx][ny] = i;
                        viruses[i].add(new Node(nx,ny));
                    }
                }
            }

            S--;
        }

    }

    private static boolean isInvalid(int nx, int ny) {

        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        map = new int[N][N];
        viruses = new ArrayDeque[K + 1];

        for (int i = 1; i <= K; i++) {
            viruses[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] != 0) {
                    viruses[map[i][j]].add(new Node(i,j));
                }

            }
        }

        input = br.readLine().split(" ");
        S = Integer.parseInt(input[0]);
        X = Integer.parseInt(input[1]);
        Y = Integer.parseInt(input[2]);


    }
}
