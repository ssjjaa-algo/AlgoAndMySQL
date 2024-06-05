package Programmers.Algorithm;

import java.util.*;

class 경주로건설 {

    int[] rDir = {-1, 0, 1, 0};
    int[] cDir = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int answer = 0;

        int[][] dp = new int[board.length][board.length];
        bfs(board, dp);
        return dp[board.length - 1][board.length - 1];
    }

    public void bfs(int[][] board, int[][] dp) {

        Queue<int[]> q = new ArrayDeque<>();
        int len = dp.length;
        for (int i = 0; i < len; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][0] = 0;

        q.add(new int[]{0, 0, 0, 1});
        q.add(new int[]{0, 0, 0, 2});

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int v = cur[2];
                int d = cur[3];

                for (int j = 0; j < 4; j++) {
                    int nr = r + rDir[j];
                    int nc = c + cDir[j];
                    if (isInvalid(nr, nc, len) || board[nr][nc] == 1) continue;

                    int cost = v;

                    if (d == j) cost += 100;
                    else cost += 600;

                    if (dp[nr][nc] >= cost) {
                        dp[nr][nc] = cost;
                        q.add(new int[]{nr, nc, cost, j});
                    }
                    else if (dp[nr][nc] + 600 >= cost) {
                        q.add(new int[]{nr, nc, cost, j});
                    }
                }
            }
        }
    }

    public boolean isInvalid(int r, int c, int len) {
        return r < 0 || r >= len || c < 0 || c >= len;
    }
}
