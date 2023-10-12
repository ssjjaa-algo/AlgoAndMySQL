package Programmers.Algorithm;

import java.util.*;

class 게임맵최단거리 {

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public int solution(int[][] maps) {
        int answer = bfs(maps);
        return answer;
    }

    public int bfs(int[][] maps) {

        int cnt = 0;
        int row = maps.length;
        int col = maps[0].length;

        int[] xDirection = {-1,0,1,0};
        int[] yDirection = {0,1,0,-1};

        boolean[][] visited = new boolean[row][col];

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0,0));
        visited[0][0] = true;

        while(!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                Point temp = q.poll();

                if (temp.x == row - 1 && temp.y == col - 1) return cnt + 1;

                for (int j = 0; j < 4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny,row,col) || visited[nx][ny] || maps[nx][ny] == 0) continue;

                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }
            cnt++;
        }

        return -1;
    }

    public boolean isIn(int nx, int ny, int row, int col) {
        if (nx < 0 || nx >= row || ny < 0 || ny >= col) return false;

        return true;
    }
}
