package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17141 {

    static class Virus {
        int x,y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static Virus[] choice;
    static int[][] map;
    static int N,M;
    static int ans = Integer.MAX_VALUE;
    static List<Virus> virusList = new ArrayList<Virus>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input;
        input = br.readLine().split(" ");


        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][N];

        choice = new Virus[M];
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i,j));
                }

                if (map[i][j] == 1) map[i][j] = -1;


            }
        }

        placeVirus(0,0);

        System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
    }

    private static void placeVirus(int cnt,int start) {

        if (cnt == M) {
            bfs();
            return;
        }

        for (int i=start; i< virusList.size(); i++) {
            choice[cnt] = virusList.get(i);
            placeVirus(cnt + 1, i + 1);
        }
    }

    private static void bfs() {

        int[][] copyMap = copy();
        boolean[][] visited = new boolean[N][N];
        Queue<Virus> q = new ArrayDeque<>();

        for (int i=0; i< M; i++) { // 바이러스 위치시키고, 큐에 삽입해준다.
            visited[choice[i].x][choice[i].y] = true;
            q.add(choice[i]);
        }
        int time = -1;

        while(!q.isEmpty()) {

            int size = q.size();
            time++;
            for (int i=0; i<size; i++) {
                Virus temp = q.poll();

                for (int j=0; j<4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny) || visited[nx][ny] || copyMap[nx][ny] != 0) continue;

                    visited[nx][ny] = true;
                    q.add(new Virus(nx,ny));
                }
            }
        }

        ans = Math.min(ans,check(copyMap));
    }

    private static int check(int[][] copyMap) {

        int max = 0;
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (copyMap[i][j] > max) max = copyMap[i][j];

                if (copyMap[i][j] == 0) cnt++;
            }
        }

        if (cnt != M) return Integer.MAX_VALUE;

        return max;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;
        return true;
    }

    private static int[][] copy() {
        int[][] copy = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 2) {
                    copy[i][j] = 0;
                    continue;
                }

                copy[i][j] = map[i][j];

            }
         }

        return copy;
    }
}
