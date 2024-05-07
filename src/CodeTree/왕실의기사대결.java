package CodeTree;

import java.io.*;
import java.util.*;

public class 왕실의기사대결 {

    static class Knight {
        int row, col, height, weight, health, k;

        public Knight(int row, int col, int height, int weight, int k) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.weight = weight;
            this.health = k;
            this.k = k;
        }
    }

    static int[][] map;
    static int[][] knightMap;
    static Knight[] knights;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};
    static int L, N, Q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        Q = Integer.parseInt(input[2]);
        map = new int[L + 1][L + 1];
        knights = new Knight[N + 1];
        knightMap = new int[L + 1][L + 1];

        for (int i = 1; i <= L; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= L; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);
            int height = Integer.parseInt(input[2]);
            int weight = Integer.parseInt(input[3]);
            int k = Integer.parseInt(input[4]);
            knights[i] = new Knight(row, col, height, weight, k);
        }

        for (int i = 1; i <= N; i++) {

            int row = knights[i].row;
            int col = knights[i].col;

            int nr = knights[i].row + knights[i].height;
            int nc = knights[i].col + knights[i].weight;

            for (int j = row; j < nr; j++) {
                for (int k = col; k < nc; k++) {
                    knightMap[j][k] = i;
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            input = br.readLine().split(" ");
            int knightNum = Integer.parseInt(input[0]);
            int direction = Integer.parseInt(input[1]);
            if (knights[knightNum].k <= 0) continue;
            command(knightNum, direction);
            initKnightMap();
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (knights[i].k > 0) {
                answer += (knights[i].health - knights[i].k);
            }
        }
        System.out.println(answer);

        br.close();
    }

    private static void initKnightMap() {

        for (int i = 1; i <= L; i++) {
            Arrays.fill(knightMap[i], 0);
        }

        for (int i = 1; i <= N; i++) {
            if (knights[i].k <= 0) continue;
            int weight = knights[i].weight;
            int height = knights[i].height;

            for (int j = knights[i].row; j < knights[i].row + height; j++) {
                for (int k = knights[i].col; k < knights[i].col + weight; k++) {
                    knightMap[j][k] = i;
                }
            }
        }

    }

    private static void command(int num, int direction) {

        if (knights[num].k <= 0) return;

        boolean[] visited = new boolean[N + 1];
        visited[num] = true;
        Queue<Knight> q = new ArrayDeque<>();
        q.add(knights[num]);


        while (!q.isEmpty()) {

            Knight cur = q.poll();
            int height = cur.height;
            int weight = cur.weight;
            int curNum = knightMap[cur.row][cur.col];

            int nr = cur.row + rDir[direction];
            int nc = cur.col + cDir[direction];

            for (int i = nr; i < nr + height; i++) {
                for (int j = nc; j < nc + weight; j++) {

                    if (isInvalid(i, j) || map[i][j] == 2) return;

                    if (knightMap[i][j] != 0 && knightMap[i][j] != curNum && !visited[knightMap[i][j]]) {
                        visited[knightMap[i][j]] = true;
                        q.add(knights[knightMap[i][j]]);
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) continue;

            knights[i].row += rDir[direction];
            knights[i].col += cDir[direction];
            if (i == num) continue;

            int weight = knights[i].weight;
            int height = knights[i].height;
            int nr = knights[i].row + height;
            int nc = knights[i].col + weight;

            for (int j = knights[i].row; j < nr; j++) {
                for (int k = knights[i].col; k < nc; k++) {
                    if (map[j][k] == 1) {
                        knights[i].k--;
                    }
                }
            }
        }


    }


    private static boolean isInvalid(int nr, int nc) {

        return nr <= 0 || nr > L || nc <= 0 || nc > L;
    }
}

