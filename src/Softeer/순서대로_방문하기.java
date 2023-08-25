package Softeer;

import java.io.*;

public class 순서대로_방문하기 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int map[][];
    static boolean[][] visited;
    static Node[] nodes;
    static int N,M;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static int res = 0;
    public static void main(String[] args) throws IOException{
        init();
        visited[nodes[0].x][nodes[0].y] = true;
        dfs(nodes[0].x,nodes[0].y, 1);
        System.out.println(res);
    }

    private static void dfs(int x, int y, int cnt) {


        if (cnt == M) {
            res++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + xDirection[i];
            int ny = y + yDirection[i];

            if (!isIn(nx,ny) || map[nx][ny] == 1 || visited[nx][ny]) {
                continue;
            }

            if (nx == nodes[cnt].x && ny == nodes[cnt].y) {
                visited[nx][ny] = true;
                dfs(nx,ny,cnt + 1);
                visited[nx][ny] = false;
            }
            else {
                visited[nx][ny] = true;
                dfs(nx, ny, cnt);
                visited[nx][ny] = false;
            }
        }
    }
    private static boolean isIn(int nx, int ny) {
        if (nx < 1 || nx > N || ny < 1 || ny > N) return false;

        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        nodes = new Node[M];

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j-1]);
            }
        }

        int x,y;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            nodes[i] = new Node(x,y);
        }


    }
}
