package CodeTree;

import java.io.*;
import java.util.*;

public class 메이즈러너 {

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean[] live;
    static int[][] map;
    static Node[] nodes;

    static int N, M, K;
    static int[] rDir = {-1, 1, 0, 0};
    static int[] cDir = {0, 0, 1, -1};
    static int[] exit = new int[2];
    static int moveCnt = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        live = new boolean[M];
        Arrays.fill(live, true);
        map = new int[N][N];
        nodes = new Node[M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int r, c;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]) - 1;
            c = Integer.parseInt(input[1]) - 1;
            nodes[i] = new Node(r, c);
        }

        input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]) - 1;
        c = Integer.parseInt(input[1]) - 1;
        exit[0] = r;
        exit[1] = c;

        calculate();
    }

    private static void calculate() {

        while(K-- > 0) {

            if (allExit()) break;
            move();
            int[] res = findMinimalSize();
            rotateMap(res);
            rotateNodes(res);
        }

        System.out.println(moveCnt);
        System.out.println((exit[0] + 1) + " " + (exit[1] + 1));
    }

    private static void rotateNodes(int[] res) {
        int r = res[0];
        int c = res[1];
        int size = res[2];

        for (int i = 0; i < M; i++) {
            if (!live[i]) continue;

            if (nodes[i].r >= r && nodes[i].r < r + size && nodes[i].c >= c && nodes[i].c < c + size) {
                int newR = nodes[i].r - r;
                int newC = nodes[i].c - c;

                int rotatedR = newC;
                int rotatedC = size - 1 - newR;

                nodes[i].r = r + rotatedR;
                nodes[i].c = c + rotatedC;
            }
        }

        int exitR = exit[0];
        int exitC = exit[1];

        if (exitR >= r && exitR < r + size && exitC >= c && exitC < c + size) {
            int newExitR = exitR - r;
            int newExitC = exitC - c;

            int rotatedExitR = newExitC;
            int rotatedExitC = size - 1 - newExitR;

            exit[0] = r + rotatedExitR;
            exit[1] = c + rotatedExitC;
        }
    }
    private static void rotateMap(int[] res) {

        int[][] temp = new int[N][N];
        int r = res[0];
        int c = res[1];
        int size = res[2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = map[i + r][j + c];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i + r][j + c] = temp[size - 1 - j][i];
                if (map[i + r][j + c] >= 1 && map[i + r][j + c] <= 9) map[i + r][j + c]--;
            }
        }

    }

    private static int[] findMinimalSize() {

        int size = 2;

        while(size <= N) {

            for (int i = 0; i <= N - size; i++) {
                for (int j = 0; j <= N - size; j++) {
                    if (exitIn(i, j, size) && nodesIn(i, j, size)) return new int[]{i, j, size};
                }
            }
            size++;
        }

        return new int[] {0, 0, -1};
    }

    private static boolean nodesIn(int r, int c, int size) {

        for (int i = 0; i < M; i++) {
            if (!live[i]) continue;
            if (nodes[i].r >= r && nodes[i].r < r + size && nodes[i].c >= c && nodes[i].c < c + size) return true;
        }
        return false;
    }

    private static boolean exitIn(int r, int c, int size) {

        return (exit[0] >= r && exit[0] < r + size) && (exit[1] >= c && exit[1] < c + size);
    }

    private static void move() {

        for (int i = 0; i < M; i++) {

            if (!live[i]) continue;
            int dir = -1;
            int d = getDistance(nodes[i].r, nodes[i].c);
            for (int j = 0; j < 4; j++) {
                int nr = nodes[i].r + rDir[j];
                int nc = nodes[i].c + cDir[j];
                if (isInvalid(nr, nc) || (map[nr][nc] >= 1 && map[nr][nc] <= 9)) continue;

                int distance = getDistance(nr, nc);

                if (distance >= d) continue;
                d = distance;
                dir = j;
                if (nr == exit[0] && nc == exit[1]) {
                    live[i] = false;
                    break;
                }
            }

            if (dir == -1) continue;

            moveCnt++;
            nodes[i].r += rDir[dir];
            nodes[i].c += cDir[dir];
        }
    }

    private static int getDistance(int r, int c) {

        return Math.abs(r - exit[0]) + Math.abs(c - exit[1]);
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static boolean allExit() {

        for (int i = 0; i < M; i++) {
            if (live[i]) return false;
        }

        return true;
    }
}
