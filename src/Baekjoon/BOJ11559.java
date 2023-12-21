package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11559 {

    static char[][] map;
    static boolean[][] visited;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(game());
    }

    private static int game() {

        int next = 1;
        int ans = -1;

        while (next != 0) {

            checkDown();

            ans++;
            next = 0;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {

                    if (map[i][j] == '.' || visited[i][j]) continue; // 색깔이 아니거나 방문 지점인 경우 pass

                    next += bfs(i, j, map[i][j]); // 탐색 시작

                }
            }

            for (int i = 0; i < 12; i++) {
                Arrays.fill(visited[i],false);
            }
        }

        return ans;
    }

    private static void checkDown() {

        for (int i = 0 ; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {

                if (map[j][i] != '.') {
                    downPuyo(j,i,map[j][i]);
                }

            }
        }

    }

    private static void downPuyo(int row, int col, char color) {

        if (!isIn(row + 1,col)) return;

        for (int i = row + 1; i < 12; i++) {

            if (map[i][col] != '.') return;

            map[i-1][col] = '.';
            map[i][col] = color;
        }
    }


    private static int bfs(int x, int y, char color) {

        Queue<Node> q= new ArrayDeque<>();
        List<Node> deleteNode = new ArrayList<Node>();
        Node target = new Node(x,y);

        visited[x][y] = true;
        q.add(target);
        deleteNode.add(target);

        int nx, ny;
        while (!q.isEmpty()) {

            Node temp = q.poll();

            for (int i = 0; i < 4; i++) {
                nx = temp.x + xDirection[i];
                ny = temp.y + yDirection[i];

                if(!isIn(nx,ny) || visited[nx][ny] || map[nx][ny] != color) continue;

                visited[nx][ny] = true;
                target = new Node(nx,ny);
                q.add(target);
                deleteNode.add(target);
            }
        }

        if (checkDelete(deleteNode)) {
            return 1;
        }

        return 0;

    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx > 11 || ny < 0 || ny > 5) return false;

        return true;
    }

    private static boolean checkDelete(List<Node> deleteNode) {

        int size = deleteNode.size();
        if (size < 4) return false;

        for (Node node : deleteNode) {
            map[node.x][node.y] = '.';
        }

        return true;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();

    }
}
