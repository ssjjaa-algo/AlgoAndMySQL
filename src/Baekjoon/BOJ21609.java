package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21609 {

    static int N, M;
    static int[][] map;
    static int[] xDirection = {-1, 0, 1, 0};
    static int[] yDirection = { 0, 1, 0, -1};

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x= x;
            this.y= y;
        }
    }
    static class Group implements Comparable<Group>{

        int x, y, rainbowCnt, allCnt;

        public Group(int x, int y, int rainbowCnt, int allCnt) {
            this.x = x;
            this.y = y;
            this.rainbowCnt = rainbowCnt;
            this.allCnt = allCnt;
        }

        @Override
        public int compareTo(Group o) {

            if (this.allCnt == o.allCnt) {

                if (this.rainbowCnt == o.rainbowCnt) {

                    if (this.x == o.x) {
                        return Integer.compare(o.y, this.y);
                    }

                    return Integer.compare(o.x, this.x);
                }

                return Integer.compare(o.rainbowCnt, this.rainbowCnt);
            }

            return Integer.compare(o.allCnt, this.allCnt);
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        List<Group> groups = new ArrayList<>();
        int score = 0;

        while(true) {

            makeGroup(groups);
            if (groups.isEmpty()) break;
            Collections.sort(groups);
            score += deleteBiggestGroup(groups.get(0));
            gravity();
            change();
            gravity();
            groups.clear();

        }

        System.out.println(score);
    }

    private static void change() {

        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[j][N - 1 - i];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }
    private static void gravity() {

        for (int i = 0; i < N; i++) {

            for (int j = N - 1; j >= 0; j--) {

                if (map[j][i] == -9 || map[j][i] == -1) continue;

                for (int k = j + 1; k < N; k++) {

                    if (map[k][i] == -9) {
                        map[k][i] = map[k - 1][i];
                        map[k - 1][i] = -9;
                    }

                    else break;
                }
            }
        }
    }

    private static int deleteBiggestGroup(Group group) {

        boolean[][] visited = new boolean[N][N];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(group.x, group.y));
        int color = map[group.x][group.y];

        while (!q.isEmpty()) {

            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + xDirection[i];
                int ny = cur.y + yDirection[i];

                if (isInvalid(nx,ny) || visited[nx][ny] || (map[nx][ny] != color && map[nx][ny] != 0)) continue;

                map[nx][ny] = -9;
                visited[nx][ny] = true;
                q.add(new Node(nx,ny));
            }
        }

        return group.allCnt * group.allCnt;
    }

    private static void makeGroup(List<Group> groups) {

        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 0 || map[i][j] == -1 || map[i][j] == -9 || visited[i][j]) continue;
                addGroup(groups, visited, i, j);
            }
        }
    }

    private static void addGroup(List<Group> groups, boolean[][] visited, int x, int y) {

        int allCnt = 1;
        int rainbowCnt = 0;

        Queue<Node> q = new ArrayDeque<>();
        List<Node> rainbows = new ArrayList<>();
        q.add(new Node(x,y));
        visited[x][y] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {

                int nx = cur.x + xDirection[i];
                int ny = cur.y + yDirection[i];

                if (isInvalid(nx,ny) || visited[nx][ny] ||
                        (map[nx][ny] != map[x][y] && map[nx][ny] != 0)) continue;

                if(map[nx][ny] == 0)  {
                    rainbows.add(new Node(nx, ny));
                    rainbowCnt++;
                }

                allCnt++;
                visited[nx][ny] = true;
                q.add(new Node(nx,ny));
            }
        }

        for (Node node : rainbows) {
            visited[node.x][node.y] = false;
        }


        if (allCnt >= 2) groups.add(new Group(x, y, rainbowCnt, allCnt));

    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

    }
}
