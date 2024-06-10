package CodeTree;

import java.io.*;
import java.util.*;

public class 정육면체한번더굴리기 {
    static int n, m;
    static int[][] map;
    static int r = 0;
    static int c = 0;

    static int up = 1;
    static int front = 2;
    static int right = 3;

    static int dir = 0;
    static int[] rDir = {0, 1, 0, -1};
    static int[] cDir = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        calculate();
    }

    private static void calculate() {

        int ans = 0;
        while(m-- > 0 ) {
            moveDice();
            ans += getScore();
            changeDir();
        }
        System.out.println(ans);
    }

    private static void changeDir() {

        int num = map[r][c];
        int bottom = 7 - up;
        if (bottom > num) { // 주사위의 아랫면이 보드의 해당 칸에 있는 숫자보다 크면
            dir = (dir + 1) % 4;
        }
        else if (bottom < num) {
            dir--;
            if (dir == -1) dir = 3;
        }
    }

    private static int getScore() {

        int score = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        q.add(new int[]{r, c});
        int num = map[r][c];
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            score += num;

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + rDir[i];
                int nc = cur[1] + cDir[i];

                if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] != num) continue;
                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }

        return score;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }

    private static void moveDice() {

        int nr = r + rDir[dir];
        int nc = c + cDir[dir];

        if (isInvalid(nr, nc)) {

            dir = (dir + 2) % 4;
            nr = r + rDir[dir];
            nc = c + cDir[dir];
        }
        r = nr;
        c = nc;
        rotateDice();
    }

    private static void rotateDice() {

        if (dir == 0) {
            int temp = right;
            right = up;
            up = 7 - temp;
        }

        else if (dir == 1) {
            int temp = front;
            front = up;
            up = 7 - temp;
        }

        else if (dir == 2) {
            int temp = up;
            up = right;
            right = 7 - temp;
        }
        else {
            int temp = up;
            up = front;
            front = 7 - temp;
        }
    }
}


