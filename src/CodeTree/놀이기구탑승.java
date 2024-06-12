package CodeTree;

import java.io.*;
import java.util.*;

public class 놀이기구탑승 {

    static List<Integer> adj[];
    static int n;
    static int[][] map;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};
    static int[] score = {0, 1, 10, 100, 1000};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n * n + 1];
        map = new int[n][n];

        for (int i = 1; i <= n * n; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i <= n * n; i++) {
            String[] input = br.readLine().split(" ");
            int student = Integer.parseInt(input[0]);
            for (int j = 1; j < 5; j++) { // 좋아하는 친구의 숫자는 4명씩 정해져있다.
                adj[student].add(Integer.parseInt(input[j]));
            }
            simulate(student);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += getScore(i, j);
            }
        }

        System.out.println(ans);
    }

    private static int getScore(int r, int c) {

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + rDir[i];
            int nc = c + cDir[i];
            if (isInvalid(nr, nc)) continue;

            for (int like : adj[map[r][c]]) {
                if (map[nr][nc] == like) {
                    cnt++;
                    break;
                }
            }
        }

        return score[cnt];
    }

    private static void simulate(int student) {

        // 격자를 벗어나지 않는 4방향으로 인접한 칸 중 좋아하는 친구의 수가 가장 많은 위치로 간다.
        int maxFriendCnt = 0;
        int maxEmptyCnt = 0;
        int r = 100, c = 100;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) continue; // 이미 어떤 학생이 차지하고 있는 경우 해당 위치로 못간다.

                int[] res = getFriendsAndEmptyPlaces(i, j, student); // 해당 위치에서 4방향으로 돌며, 내가 좋아하는 학생들이 빈칸에 위치해있는지 확인한다.
                int friendCnt = res[0];
                int emptyCnt = res[1];
                if (friendCnt > maxFriendCnt || (friendCnt == maxFriendCnt) && (emptyCnt > maxEmptyCnt)) { // 1, 2조건
                    maxFriendCnt = friendCnt;
                    maxEmptyCnt = emptyCnt;
                    r = i;
                    c = j;
                }

                else if (friendCnt == maxFriendCnt && emptyCnt == maxEmptyCnt) { // 3, 4조건
                    if (r > i || (r == i) && (c > j)) {
                        r = i;
                        c = j;
                    }
                }
            }
        }

        map[r][c] = student;
    }

    private static int[] getFriendsAndEmptyPlaces(int r, int c, int student) {

        int friendCnt = 0;
        int emptyCnt = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + rDir[i];
            int nc = c + cDir[i];
            if (isInvalid(nr, nc)) continue;

            if (map[nr][nc] == 0) emptyCnt++;
            else {
                for (int like : adj[student]) {
                    if (map[nr][nc] == like) {
                        friendCnt++;
                        break;
                    }
                }
            }
        }

        return new int[]{friendCnt, emptyCnt};
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}

