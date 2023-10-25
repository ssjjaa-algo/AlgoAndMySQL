package Programmers.Algorithm;

import java.util.*;

class 무인도여행 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    static char[][] map;
    static boolean[][] visited;
    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();

        init(maps);

        int row = maps.length;
        int col = maps[0].length();
        int ans = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if(visited[i][j] || map[i][j] == 'X') continue;

                ans = bfs(i,j,row,col);
                if (ans == 0) continue;
                list.add(ans);

            }
        }

        if (list.size() == 0) {
            return new int[]{-1};
        }

        int[] answer = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        Arrays.sort(answer);

        return answer;
    }

    public int bfs(int x, int y, int row, int col) {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x,y));
        visited[x][y] = true;
        int sum = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0 ; i < size; i++) {
                Node temp = q.poll();
                sum += map[temp.x][temp.y] - '0';

                for (int j = 0; j < 4; j++) {
                    int nx = temp.x + xDirection[j];
                    int ny = temp.y + yDirection[j];

                    if (!isIn(nx,ny,row,col) || visited[nx][ny] || map[nx][ny] == 'X')
                        continue;

                    q.add(new Node(nx,ny));
                    visited[nx][ny] = true;

                }
            }
        }

        return sum;
    }

    public boolean isIn(int nx, int ny, int row, int col) {

        if (nx < 0 || nx >= row || ny < 0 || ny >= col) return false;

        return true;

    }

    public void init(String[] maps) {

        map = new char[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];

        for (int i = 0 ; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

    }
}
