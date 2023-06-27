package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ17142 {

    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] xDirection = { -1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    static int ans = Integer.MAX_VALUE;
    static int N,M;
    static int[][] map;
    static List<Coordination> virusList = new ArrayList<>();
    static Coordination[] choiceVirus;
    static List<Coordination> wallList = new ArrayList<>();
    static int blank = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][N];
        choiceVirus = new Coordination[M];

        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                if (map[i][j] == 1) { // 벽인 경우
                    wallList.add(new Coordination(i,j));
                }

                else if (map[i][j] == 2) { // 바이러스를 놓을 수 있는 위치인 경우
                    virusList.add(new Coordination(i,j));
                }

                else {
                    blank++;
                }
            }
        }
        if (blank == 0) {
            System.out.println(0);
            return;
        }

        dfs(0,0);

        sb.append(ans == Integer.MAX_VALUE ? -1 : ans);

        wr.write(sb.toString());
        wr.close();
        br.close();
    }

    private static void dfs(int start, int cnt) {

        if (cnt == M) {
            ans = Math.min(ans,bfs());
            return;
        }

        for (int i=start; i < virusList.size(); i++) {
            choiceVirus[cnt] = virusList.get(i);
            dfs(i + 1, cnt + 1);
        }

    }

    private static int bfs() {

        boolean[][] check = new boolean[N][N];
        int[][] copyMap = new int[N][N];
        Queue<Coordination> q = new ArrayDeque<>();
        copy(copyMap);

        Coordination temp;
        for (int i=0; i<M; i++) { // 선택된 바이러스들을 방문처리한다.
            temp = choiceVirus[i];
            copyMap[temp.x][temp.y] = 0;
            check[temp.x][temp.y] = true;
            q.add(temp);
        }

        for (int i=0; i<wallList.size(); i++) { // 벽들을 방문처리한다.
            temp = wallList.get(i);
            check[temp.x][temp.y] = true;
        }

        int time = 0;
        int cnt = blank;
        while(!q.isEmpty()) {
            int size = q.size();

            for (int i=0; i<size; i++) {

                temp = q.poll();

                for (int j=0; j<4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny) || check[nx][ny]) continue;

                    check[nx][ny] = true;
                    q.add(new Coordination(nx,ny));

                    if (copyMap[nx][ny] == 0) cnt--;
                }
            }
            time++;

            if (cnt == 0) return time;
        }
        return Integer.MAX_VALUE;

    }

    private static int[][] copy(int[][] copyMap) {

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >=N || ny < 0 || ny >= N) return false;

        return true;
    }
}
