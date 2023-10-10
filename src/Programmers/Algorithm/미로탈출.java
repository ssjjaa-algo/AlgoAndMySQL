package Programmers.Algorithm;

import java.util.*;

class 미로탈출 {

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    static char[][] map;
    static Point start;
    static Point lever;

    public int solution(String[] maps) {

        map = new char[maps.length][maps[0].length()];

        for (int i = 0; i < map.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        init();

        int findLever = bfs(start,'L', maps);
        int findEnd = bfs(lever,'E', maps);

        if (findLever == -1 || findEnd == -1) return -1;
        return findLever + findEnd;
    }

    public int bfs(Point p, char end, String[] maps) {

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];


        visited[p.x][p.y] = true;
        q.add(p);
        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Point temp = q.poll();

                if(map[temp.x][temp.y] == end) {
                    return cnt;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny) || visited[nx][ny] || map[nx][ny] == 'X') continue;

                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }
            cnt++;
        }
        return -1;
    }

    public void init() {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == 'S') {
                    start = new Point(i,j);
                }

                else if (map[i][j] == 'L') {
                    lever = new Point(i,j);
                }
            }
        }
    }

    public boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) return false;

        return true;
    }

}
