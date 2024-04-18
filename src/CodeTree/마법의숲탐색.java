package CodeTree;

import java.io.*;
import java.util.*;

public class 마법의숲탐색 {

    static class Node {
        int row, col, exit, number;

        public Node(int row, int col, int exit, int number) {
            this.row = row;
            this.col = col;
            this.exit = exit;
            this.number = number;
        }
    }

    static int R, C, K;
    static int[] moveRowDirection = {1, 0, 0};
    static int[] moveColDirection = {0, -1, 1};
    static int[] rDirection = {-1, 0, 1, 0};
    static int[] cDirection = {0, 1, 0, -1};
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        init();
        calculate();
        br.close();

    }

    private static void calculate() throws IOException {

        Queue<Node> q = new ArrayDeque<>();
        int ans = 0;
        for (int tc = 0; tc < K; tc++) {
            String[] input = br.readLine().split(" ");
            int col = Integer.parseInt(input[0]);
            int direction = Integer.parseInt(input[1]);
            Node node = new Node(1, col, direction, tc);

            move(node);
            if (node.row <= 3) {
                initMap();
                continue;
            }

            fillGolem(node, tc);
            ans += bfs(node, q);
            q.clear();
        }
        System.out.println(ans);
    }

    private static void fillGolem(Node node, int tc) {

        for (int i = 0; i < 4; i++) {
            int nr = node.row + rDirection[i];
            int nc = node.col + cDirection[i];
            map[nr][nc] = tc;
        }

        map[node.row][node.col] = tc;
        map[node.row + rDirection[node.exit]][node.col + cDirection[node.exit]] = tc + 1000;
    }

    private static int bfs(Node node, Queue<Node> q) {
        q.add(node);
        boolean[][] visited = new boolean[R + 3][C + 1];
        visited[node.row][node.col] = true;

        int max = node.row;
        while (!q.isEmpty()) {

            Node cur = q.poll();
            max = Math.max(max, cur.row);

            for (int i = 0; i < 4; i++) {
                int nr = cur.row + rDirection[i];
                int nc = cur.col + cDirection[i];

                if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;

                if (map[nr][nc] == cur.number || map[nr][nc] == cur.number + 1000) {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc, 0, map[nr][nc]));
                }

                else if (map[nr][nc] != cur.number && cur.number >= 1000) {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc, 0, map[nr][nc]));
                }

            }
        }
        return max - 2;
    }

    private static void move(Node node) {

        while(true) {

            if (south(node, 0)) continue;
            else if (other(node, 1)) continue;
            else if (other(node, 2)) continue;

            break;
        }

    }

    private static boolean other(Node node, int direction) {

        int row = node.row + moveRowDirection[direction];
        int col = node.col + moveColDirection[direction];

        if (isCenterInvalid(row, col) || map[row][col] != -1) return false;

        for (int i = 0; i < 4; i++) {
            int nr = row + rDirection[i];
            int nc = col + cDirection[i];
            if (map[nr][nc] != -1) return false;
        }

        int newExit = node.exit;
        Node newNode = new Node(row, col, direction == 1 ? --newExit : ++newExit, node.number);
        if (!south(newNode, 0)) return false;

        node.row = newNode.row;
        node.col = newNode.col;
        node.exit = newNode.exit;

        return true;
    }


    private static boolean south(Node node, int direction) {
        int row = node.row + moveRowDirection[direction];
        int col = node.col + moveColDirection[direction];

        if (isCenterInvalid(row, col) || map[row][col] != -1) return false;

        for (int i = 0; i < 4; i++) {
            int nr = row + rDirection[i];
            int nc = col + cDirection[i];
            if (map[nr][nc] != -1) return false;
        }

        node.row = row;
        node.col = col;
        if (direction == 1) node.exit--;
        else if (direction == 2) node.exit++;

        if (node.exit < 0) node.exit = 3;
        else if (node.exit > 3) node.exit = 0;

        return true;
    }

    private static void initMap() {

        for (int i = 0; i < R + 3; i++) {
            for (int j = 0; j < C + 1; j++) {
                map[i][j] = -1;
            }
        }

    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r > R + 2 || c < 1 || c > C;
    }

    private static boolean isCenterInvalid(int r, int c) {

        return (r >= R + 2) || (c >= C) || (c <= 1); // 골렘의 정중앙 가능 판별
    }

    private static void init() throws IOException {

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new int[R + 3][C + 1];
        initMap();
    }
}

