package Baekjoon;

import java.io.*;
import java.util.*;


public class BOJ14466 {
    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int []xDirection = {-1,0,1,0};
    static int []yDirection = {0,1,0,-1};
    static int []reverse = {2,3,0,1};
    static Coordination[] cowArr;
    static boolean[][] positionOfCow;
    static boolean[][][] map;
    static String[] input;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int R = Integer.parseInt(input[2]);

        init(N,K);
        buildRoad(R);
        placeCow(K);

        for (int i=0; i<K; i++) {
            bfs(i,N);
        }

        int ans = 0;
        for (int i=1; i < K; i++) {
            ans +=i;
        }
        System.out.println(ans - (cnt / 2));
    }

    private static void bfs(int num, int N) {

        Queue<Coordination> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][N+1];

        visited[cowArr[num].x][cowArr[num].y] = true;
        q.add(cowArr[num]);

        while(!q.isEmpty()) {
            Coordination temp = q.poll();

            for (int i=0; i<4; i++) {
                int nx = temp.x + xDirection[i];
                int ny = temp.y + yDirection[i];

                // 범위 안에 들어와있지 않거나 이미 방문한 지점이거나 가려는 방향이 다리를 건너야 한다면
                if (!isIn(nx,ny,N) || visited[nx][ny] || map[temp.x][temp.y][i]) continue;

                if (positionOfCow[nx][ny]) cnt++;

                visited[nx][ny] = true;
                q.add(new Coordination(nx,ny));
            }
        }
    }

    private static boolean isIn(int nx, int ny, int N) {
        if (nx < 1 || nx > N || ny < 1 || ny > N) return false;

        return true;
    }

    private static void placeCow(int K) throws IOException{

        int x,y;
        for (int i = 0 ; i < K; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);

            positionOfCow[x][y] = true;
            cowArr[i] = new Coordination(x,y);
        }
    }

    private static void init(int N, int K) {
        map = new boolean[N+1][N+1][4];
        positionOfCow = new boolean[N+1][N+1];
        cowArr = new Coordination[K];
    }

    private static void buildRoad(int R) throws IOException{

        int r1,c1,r2,c2;
        for (int i=0; i<R; i++) {
            input = br.readLine().split(" ");
            r1 = Integer.parseInt(input[0]);
            c1 = Integer.parseInt(input[1]);
            r2 = Integer.parseInt(input[2]);
            c2 = Integer.parseInt(input[3]);

            //4방에 대하여
            for (int j=0; j<4; j++) {
                //어느 방향으로 인접하는지 체크
                if (r1 + xDirection[j] == r2 && c1 + yDirection[j] == c2) {
                    map[r1][c1][j] = true;
                    map[r2][c2][reverse[j]] = true;
                    break;
                }
            }
        }
    }
}
