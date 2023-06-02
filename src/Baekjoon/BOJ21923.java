package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ21923 {

    static class pair {
        int x,y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N,M; // 세로길이 N, 가로길이 M
    static int[][] matrix;
    static int[] Xarr = {-1,0};
    static int[] Yarr = {0,1};
    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        matrix = new int[N][M];

        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        long [][] leftMatrix = bfs(N-1,0,1);
        long [][] rightMatrix = bfs(N-1,M-1,-1);

        long max = -Long.MAX_VALUE;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                max = Math.max(leftMatrix[i][j] + rightMatrix[i][j], max);
            }
        }

        System.out.println(max);
    }

    private static long [][] bfs(int startX, int startY, int number) {
        long [][] dpMatrix = new long[N][M];


        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++)
                dpMatrix[i][j] = -Long.MAX_VALUE;
        }

        dpMatrix[startX][startY] = matrix[startX][startY];

        Queue<pair> Q = new ArrayDeque<pair>();

        Q.add(new pair(startX,startY));

        int nx,ny;
        while(!Q.isEmpty()) {
            pair temp = Q.poll();

            for (int i=0; i<2; i++) {
                nx = temp.x + Xarr[i];
                ny = temp.y + (Yarr[i] * number);

                if (!isIn(nx,ny)) continue;

                if (dpMatrix[nx][ny] < matrix[nx][ny] + dpMatrix[temp.x][temp.y])
                    dpMatrix[nx][ny] = matrix[nx][ny] + dpMatrix[temp.x][temp.y];

                else continue;

                Q.add(new pair(nx,ny));

            }

        }

        return dpMatrix;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return false;

        return true;
    }


    /*
    수가 적혀있지 않은 칸의 점수는 0

    1. 시작점에서 출발하는 bfs
        - 이동 가능 위, 오른쪽

    2. 도착점에서 출발하는 bfs
        - 이동 가능 위, 왼쪽

    common
        해당 점에 대해서 bfs 시작,
        leftmatrix[nx][ny] < matrix[nx][ny] + leftmatrix[temp.x][temp.y]

        rightmatrix[nx][ny] < matrix[nx][ny] + rightmatrix]p[temp.x][temp.y]

    3. N이 1보다 크고, 시작점과 도착점이 같은 경우

    최종 합
    for (int i=0; i<N; i++)
        for (int j=0; j<M; j++)
            Math.max(leftMatrix[i][j] + rightMatrix[i][j], max);

     */
}
