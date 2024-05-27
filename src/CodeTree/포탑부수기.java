package CodeTree;

import java.io.*;
import java.util.*;

public class 포탑부수기 {

    static class Tower {
        int offensePower;
        int attackingTime;
        boolean attacked;

        public Tower(int offensePower, int attackingTime, boolean attacked) {
            this.offensePower = offensePower;
            this.attackingTime = attackingTime;
            this.attacked = false;
        }
    }

    static class Path {

        int row, col;
        List<int[]> paths = new ArrayList<>();

        public Path(int row, int col, List<int[]> paths) {
            this.row = row;
            this.col = col;
            this.paths = paths;
        }
    }

    static int[] rDir = {0, 1, 0, -1};
    static int[] cDir = {1, 0, -1, 0};

    static int[] r8Dir = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] c8Dir = {-1, 0, 1, 1, 1, 0, -1, -1};

    static Tower[][] map;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new Tower[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = new Tower(Integer.parseInt(input[j - 1]), -1, false);
            }
        }

        calculate();
    }

    private static void calculate() {

        for (int i = 0; i < K; i++) {

            if (finish()) break;
            int[] attacker = getAttacker(i);
            int[] target = getTarget(attacker, i);
            attack(attacker, target);

            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    if (map[j][k].offensePower <= 0 || map[j][k].attacked) continue;
                    map[j][k].offensePower++;
                }
            }

            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    map[j][k].attacked = false;
                }
            }

        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                max = Math.max(max, map[i][j].offensePower);
            }
        }
        System.out.println(max);


    }

    private static boolean finish() {

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j].offensePower <= 0) continue;

                ans++;
            }
        }

        return ans <= 1;
    }

    private static boolean raser(int[] attacker, int[] target) {

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[attacker[0]][attacker[1]] = true;

        Queue<Path> q = new ArrayDeque<>();
        q.add(new Path(attacker[0], attacker[1], new ArrayList<>()));

        while(!q.isEmpty()) {

            Path path = q.poll();
            if (path.row == target[0] && path.col == target[1]) {
                List<int[]> ans = path.paths;

                for (int i = 0; i < ans.size() - 1; i++) {
                    map[ans.get(i)[0]][ans.get(i)[1]].offensePower -= (map[attacker[0]][attacker[1]].offensePower / 2);
                    map[ans.get(i)[0]][ans.get(i)[1]].attacked = true;
                }
                map[target[0]][target[1]].offensePower -= map[attacker[0]][attacker[1]].offensePower;
                map[target[0]][target[1]].attacked = true;
                return true;
            }

            for (int i = 0; i < 4; i++) {

                int nr = path.row + rDir[i];
                int nc = path.col + cDir[i];

                if (nr > N) nr = 1;
                else if (nr < 1) nr = N;
                if (nc > M) nc = 1;
                else if (nc < 1) nc = M;

                if (visited[nr][nc] || map[nr][nc].offensePower <= 0) continue;
                visited[nr][nc] = true;
                List<int[]> list = new ArrayList<>(path.paths);
                list.add(new int[]{nr, nc});
                q.add(new Path(nr, nc, list));
            }
        }

        return false;
    }

    private static void shell(int[] attacker, int[] target) {

        for (int i = 0; i < 8; i++) {

            int nr = target[0] + r8Dir[i];
            int nc = target[1] + c8Dir[i];

            if (nr > N) nr = 1;
            else if (nr < 1) nr = N;
            if (nc > M) nc = 1;
            else if (nc < 1) nc = M;

            if (nr == attacker[0] && nc == attacker[1]) continue;
            map[nr][nc].offensePower -= (map[attacker[0]][attacker[1]].offensePower / 2);
            map[nr][nc].attacked = true;
        }

        map[target[0]][target[1]].offensePower -= map[attacker[0]][attacker[1]].offensePower;
        map[target[0]][target[1]].attacked = true;

    }

    private static void attack(int[] attacker, int[] target) {

        if (raser(attacker, target)) return;
        shell(attacker, target);

    }

    private static int[] getTarget(int[] attacker, int time) {
        int row = 1000;
        int col = 1000;
        int attackPower = -9999;
        int nearestTime = 1000;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                if (i == attacker[0] && j == attacker[1]) continue;

                int power = map[i][j].offensePower;

                if (power == 0) continue;

                if (power > attackPower) {
                    attackPower = power;
                    row = i;
                    col = j;
                    nearestTime = map[i][j].attackingTime;
                }

                else if (power == attackPower) {

                    int t1 = time - map[i][j].attackingTime;
                    int t2 = time - nearestTime;

                    if (t1 > t2) {
                        attackPower = power;
                        row = i;
                        col = j;
                        nearestTime = map[i][j].attackingTime;
                    }
                    else if (t1 == t2) {

                        if (i + j < row + col) {
                            attackPower = power;
                            row = i;
                            col = j;
                            nearestTime = map[i][j].attackingTime;
                        }
                        else if (i + j == row + col) {

                            if (j < col) {
                                attackPower = power;
                                row = i;
                                col = j;
                                nearestTime = map[i][j].attackingTime;
                            }
                        }
                    }
                }
            }
        }

        return new int[]{row, col};

    }

    private static int[] getAttacker(int time) {

        int row = -1;
        int col = -1;
        int attackPower = 9999;
        int nearestTime = -2;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                int power = map[i][j].offensePower;
                if (power <= 0) continue; // 부서진 포탑 제외

                if (attackPower > power) {
                    attackPower = power;
                    row = i;
                    col = j;
                    nearestTime = map[i][j].attackingTime;
                }

                else if (power == attackPower) {

                    int t1 = time - map[i][j].attackingTime;
                    int t2 = time - nearestTime;

                    if (t1 < t2) {
                        attackPower = power;
                        row = i;
                        col = j;
                        nearestTime = map[i][j].attackingTime;
                    }
                    else if (t1 == t2) {

                        if (i + j > row + col) {
                            attackPower = power;
                            row = i;
                            col = j;
                            nearestTime = map[i][j].attackingTime;
                        }
                        else if (i + j == row + col) {

                            if (j > col) {
                                attackPower = power;
                                row = i;
                                col = j;
                                nearestTime = map[i][j].attackingTime;
                            }
                        }
                    }
                }
            }
        }
        map[row][col].attackingTime = time;
        map[row][col].offensePower += (N + M);
        map[row][col].attacked = true;

        return new int[]{row, col};
    }
}