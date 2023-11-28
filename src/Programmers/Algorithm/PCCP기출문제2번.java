package Programmers.Algorithm;

import java.util.*;

class PCCP기출문제2번 {

    static int n,m;
    static int[] scores;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static boolean[] visitedCol;

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public int solution(int[][] land) {

        init(land);
        calculate(land);

        return Arrays.stream(scores).max().orElseThrow();
    }

    public void calculate(int[][] land) {

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if(land[i][j] == 0 || visited[i][j]) continue;

                bfs(i,j,land,visited);
            }
        }

    }

    public void bfs(int x, int y, int[][] land, boolean[][] visited) {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y));
        visited[x][y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {

            Node temp = q.poll();
            visitedCol[temp.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + xDirection[i];
                int ny = temp.y + yDirection[i];

                if (!isIn(nx,ny) || visited[nx][ny] || land[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.add(new Node(nx,ny));
            }

            cnt++;
        }

        for (int i = 0; i < m; i++) {
            if(visitedCol[i]) scores[i] += cnt;
        }

        Arrays.fill(visitedCol,false);
    }

    public boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;

        return true;
    }


    public void init(int[][] land) {

        n = land.length;
        m = land[0].length;
        scores = new int[m];
        visitedCol = new boolean[m];

    }

}
