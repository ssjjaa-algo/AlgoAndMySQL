package CodeTree;

import java.io.*;
import java.util.*;

public class 팩맨 {

    static int[] rDir = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] cDir = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] bossRowDir = {-1, 0, 1, 0};
    static int[] bossColDir = {0, -1, 0, 1};

    static class Node {
        int r, c, dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static int[] boss = new int[2];
    static List<Integer>[][] monsterMap = new ArrayList[5][5];
    static List<Integer>[][] copyMap = new ArrayList[5][5];
    static List<Node> moveList = new ArrayList<>();
    static int[][] deadMap = new int[5][5];
    static int m, t;
    static int eat;
    static int[] bossMove = new int[3];
    static boolean[][] visited = new boolean[5][5];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        t = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        boss[0] = Integer.parseInt(input[0]);
        boss[1] = Integer.parseInt(input[1]);

        for (int i =1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                monsterMap[i][j] = new ArrayList<>();
                copyMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]) - 1;
            monsterMap[r][c].add(d);
        }

        calculate();
    }

    private static void calculate() {

        while(t-- > 0) {
            copy();
            moveMonsters();
            moveBoss();
            cleanDeadMap();
            completeCopy();
        }

        int ans = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                ans += monsterMap[i][j].size();
            }
        }

        System.out.println(ans);
    }

    private static void completeCopy() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int num : copyMap[i][j]) {
                    monsterMap[i][j].add(num);
                }
                copyMap[i][j].clear();
            }
        }
    }

    private static void cleanDeadMap() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {

                if (deadMap[i][j] == 0) continue;
                deadMap[i][j]--;
            }
        }
    }

    private static void moveBoss() {

        eat = -1;
        for (int i = 1; i <= 4; i++) Arrays.fill(visited[i], false);
        dfs(0, 0, boss[0], boss[1], new int[3]);
        for (int i = 0; i < 3; i++) { // 가져온 방향대로 이동
            boss[0] += bossRowDir[bossMove[i]];
            boss[1] += bossColDir[bossMove[i]];

            if (monsterMap[boss[0]][boss[1]].size() != 0) {
                deadMap[boss[0]][boss[1]] = 3; // 해당 위치에 시체를 뿌려둔다.
            }

            monsterMap[boss[0]][boss[1]].clear();
        }
    }

    private static void dfs(int cnt, int kill, int r, int c, int[] arr) {

        if (cnt == 3) {
            if (kill > eat) {
                eat = kill;
                for (int i = 0; i < 3; i++) {
                    bossMove[i] = arr[i];
                }

            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + bossRowDir[i];
            int nc = c + bossColDir[i];
            if (isInvalid(nr, nc)) continue;
            arr[cnt] = i;
            if (!visited[nr][nc]) { // 최초 방문에만 monster 개수 계산
                visited[nr][nc] = true;
                dfs(cnt + 1, kill + monsterMap[nr][nc].size(), nr, nc, arr);
                visited[nr][nc] = false;
            }
            else {
                dfs(cnt + 1, kill, nr, nc, arr); // 다시 방문할 때 monster의 개수 증가시키지 않기 위해.
            }
            arr[cnt] = 0;
        }
    }


    private static void moveMonsters() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {

                int size = monsterMap[i][j].size() - 1;
                for (int k = size; k >= 0; k--) {
                    int dir = move(i, j, monsterMap[i][j].get(k)); // i, j번째 위치한 k번째 몬스터가 이동 가능한지 판별. -1이라면 이동 불가
                    if (dir == -1) {
                        continue; // 이동 불가는 움직이지 않는다.
                    }
                    monsterMap[i][j].remove(k); // 현재 위치에서 이동이 성공한 monster를 삭제시켜준다.
                }
            }
        }

        for (Node node : moveList) { // 이동한 monster들 갱신
            monsterMap[node.r][node.c].add(node.dir);
        }

        moveList.clear();
    }

    private static int move(int r, int c, int dir) {

        for (int i = dir; i < dir + 8; i++) {

            int nr = r + rDir[i % 8];
            int nc = c + cDir[i % 8];

            if (isInvalid(nr, nc) || bossPosition(nr, nc) || deadMap[nr][nc] != 0) continue;
            moveList.add(new Node(nr, nc, i % 8)); // 이동할 수 있는 경우 moveList에 추가시켜준다.
            return dir;
        }


        return -1;
    }

    private static boolean bossPosition(int r, int c) {
        return (r == boss[0] && c == boss[1]);
    }

    private static boolean isInvalid(int r, int c) {
        return r < 1 || r > 4 || c < 1 || c > 4;
    }

    private static void copy() {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                copyMap[i][j] = new ArrayList<>(monsterMap[i][j]);
            }
        }
    }
}

