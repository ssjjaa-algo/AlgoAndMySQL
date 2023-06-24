package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ17472 {

    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordination{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost,o.cost);
        }
    }

    static int cntOfIslands = 0;
    static int[][] map;
    static int N,M;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = { 0,1,0,-1};
    static int[] parents;
    static int ans = Integer.MAX_VALUE;
    static boolean[][] visited;
    static List<Edge> edgeList = new ArrayList<>();
     public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr= new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0 ; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }


        cntOfIslands = countOfIsland(); // 섬들을 체크하고 각 섬에 번호를 붙여준다.
        parents = new int[cntOfIslands + 1];

        for (int i=0; i<N; i++) {

            for (int j=0; j<M; j++) {
                if (map[i][j] != 0) {// 어떤 섬을 선택한 경우라면
                    connect(i,j,map[i][j]);
                }
            }
        }
        ans = Math.min(ans,kruskal());

         System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void connect(int x, int y, int numOfIsland) {

         for (int i=0; i<4; i++) {
             int nx = x;
             int ny = y;
             int cnt =0;
             while(true) {
                 nx = nx + xDirection[i];
                 ny = ny + yDirection[i];

                 if (!isIn(nx,ny) || map[nx][ny] == numOfIsland) break;

                 if (map[nx][ny] == 0) cnt++;

                 else if (map[nx][ny] != numOfIsland ) {
                     if (cnt >= 2) {
                         edgeList.add(new Edge(numOfIsland, map[nx][ny], cnt));
                         edgeList.add(new Edge(map[nx][ny],numOfIsland,cnt));
                     }
                     break;
                 }
             }
         }
    }
    private static int kruskal() {
        Collections.sort(edgeList);
        int size = edgeList.size();
        for (int i=1; i<= cntOfIslands; i++) {
            parents[i] = i;
        }

        int cnt = 0;
        int res = 0;
        for (int i=0; i < size; i++) {

            Edge e = edgeList.get(i);

            if (union(e.from,e.to)) {
                res += e.cost;
                if (++cnt == cntOfIslands - 1) {
                    return res;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int find(int num) {
         if(parents[num] == num) return num;
         return parents[num] = find(parents[num]);
    }

    private static boolean union(int a, int b) {
         int aRoot = find(a);
         int bRoot = find(b);

         if (aRoot == bRoot) return false;

         if (aRoot > bRoot) {
             parents[aRoot] = bRoot;
         }
         else {
             parents[bRoot] = aRoot;
         }
         return true;
    }

    private static int countOfIsland(){

        int numOfIsland = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i,j,++numOfIsland); // 섬들 분리
                }
            }
        }

        return numOfIsland;
    }

    private static void bfs(int x, int y, int numOfIsland) {


        Queue<Coordination> q = new ArrayDeque<>();
        q.add(new Coordination(x,y));
        visited[x][y] = true;
        map[x][y] = numOfIsland;

        while(!q.isEmpty()) {
            Coordination temp = q.poll();

            for (int i=0; i<4; i++) {
                int nx = temp.x + xDirection[i];
                int ny = temp.y + yDirection[i];

                if (!isIn(nx,ny) || visited[nx][ny] || map[nx][ny] != 1) continue;

                map[nx][ny] = numOfIsland;
                visited[nx][ny] = true;
                q.add(new Coordination(nx,ny));
            }
        }

    }
    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return false;
        return true;
    }
}
