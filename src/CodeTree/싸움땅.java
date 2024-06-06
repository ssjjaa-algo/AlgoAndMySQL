package CodeTree;

import java.io.*;
import java.util.*;

public class 싸움땅 {

    static int n, m, k;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};
    static PriorityQueue<Integer>[][] gunMap;
    static int[][] map;
    static Player[] player;
    static class Player {

        int r, c, d, s, gun, point;

        public Player(int r, int c, int d, int s, int gun) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.s = s;
            this.gun = gun;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        gunMap = new PriorityQueue[n + 1][n + 1];
        player = new Player[m + 1];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                gunMap[i][j] = new PriorityQueue<>(Collections.reverseOrder());
                gunMap[i][j].add(Integer.parseInt(input[j - 1]));
            }
        }

        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < 4; j++) {
                int r = Integer.parseInt(input[0]);
                int c = Integer.parseInt(input[1]);
                int d = Integer.parseInt(input[2]);
                int s = Integer.parseInt(input[3]);
                map[r][c] = i;
                player[i] = new Player(r, c, d, s, 0);
            }
        }
        calculate();
    }

    private static void calculate() {

        while(k-- > 0) {
            move();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            sb.append(player[i].point).append(" ");
        }
        System.out.print(sb);
    }

    private static void move() {

        for (int i = 1; i <= m; i++) {

            int r = player[i].r;
            int c = player[i].c;
            map[r][c] = 0; // 원래 위치 초기화

            int nr = r + rDir[player[i].d];
            int nc = c + cDir[player[i].d];

            if (isInvalid(nr, nc)) { // 방향 전환이 필요한 경우 (격자 범위 밖으로 나간 경우)
                player[i].d = (player[i].d + 2) % 4; // 반대 방향 전환
                nr = r + rDir[player[i].d];
                nc = c + cDir[player[i].d];
            }

            player[i].r = nr;
            player[i].c = nc;

            if (map[nr][nc] == 0) { // 플레이어가 없는 경우
                if (!gunMap[nr][nc].isEmpty()) { // 해당 칸에 총이 있는 경우
                    if (player[i].gun != 0) {
                        gunMap[nr][nc].add(player[i].gun); // 내 총을 현재 위치에 두고
                    }
                    player[i].gun = gunMap[nr][nc].poll(); // 공격력이 가장 강한 총으로 바꾼다.
                }
                map[nr][nc] = i; // 선택한 위치로 이동
            }

            else { // 플레이어가 있는 경우

                int winner = fight(i, map[nr][nc]);
                int loser = (winner == i) ? map[nr][nc] : i;

                map[nr][nc] = winner; // 현재 위치를 승자의 위치로 갱신
                int score = (player[winner].s + player[winner].gun) - (player[loser].s + player[loser].gun);
                player[winner].point += score; // 포인트 획득
                if (player[loser].gun != 0) {
                    gunMap[nr][nc].add(player[loser].gun); // 진 플레이어는 본인의 총을 해당 격자에 놔둔다. 0점은 그냥 놔둬도 상관 없다.
                }
                player[loser].gun = 0;

                if (!gunMap[nr][nc].isEmpty()) {
                    if (player[winner].gun != 0) {
                        gunMap[nr][nc].add(player[winner].gun);
                    }
                    player[winner].gun = gunMap[nr][nc].poll();
                }

                moveEmptyPlace(loser);
            }
        }
    }

    private static void moveEmptyPlace(int num) {
        Player loser = player[num];
        while (true) {
            int nr = loser.r + rDir[loser.d];
            int nc = loser.c + cDir[loser.d];

            if (isInvalid(nr, nc) || map[nr][nc] != 0) { // 이동할 수 없는 칸 : 누가 있거나, 격자 밖이거나
                loser.d = (loser.d + 1) % 4; // 우측 90도 회전
                continue;
            }

            loser.r = nr;
            loser.c = nc;
            map[nr][nc] = num;
            if (!gunMap[nr][nc].isEmpty()) { // 해당 칸에 총이 있는 경우
                loser.gun = gunMap[nr][nc].poll();
            }

            return;
        }
    }

    private static int fight(int mover, int existingPlayer) {

        int moverStat = player[mover].s;
        int moverGun = player[mover].gun;
        int moverSum = moverStat + moverGun;

        int existingPlayerStat = player[existingPlayer].s;
        int existingPlayerGun = player[existingPlayer].gun;
        int existingPlayerSum = existingPlayerStat + existingPlayerGun;

        if (moverSum > existingPlayerSum) return mover;
        else if (moverSum < existingPlayerSum) return existingPlayer;
        else {
            if (player[mover].s > player[existingPlayer].s) return mover;
            else return existingPlayer;
        }
    }

    private static boolean isInvalid(int r, int c) {

        return r < 1 || r > n || c < 1 || c > n;
    }
}
