package CodeTree;

import java.io.*;
import java.util.*;

public class 예술성 {
    static int n;
    static int[][] map;
    static List<Node> groups[] = new ArrayList[841];
    static boolean[][] visited;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        calculate();
    }

    private static void calculate() {

        int count = 4;
        int score = 0;
        while (count-- > 0) {
            visited = new boolean[n][n];
            for (int i = 0; i < 841; i++) groups[i] = new ArrayList<>();
            int groupIdx = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    makeGroup(i, j, groupIdx++); // 그룹 만들기
                }
            }

            int roundScore = 0;
            for (int i = 0; i < groupIdx; i++) { // 그룹 매칭해보기. 최대 841개의 배열에 대하여 n^2이므로 대략 100만
                for (int j = i + 1; j < groupIdx; j++) {

                    int adjLineCnt = bfs(i, j);
                    if (adjLineCnt == 0) continue; // 맞닿아 있는 변의 수가 0이라면 점수를 획득하지 못함.
                    roundScore += getScore(i, j, adjLineCnt);
                }
            }
            score += roundScore;
            rotate();
        }

        System.out.println(score);

    }

    private static int getScore(int a, int b, int cnt) {

        int aSize = groups[a].size();
        int bSize = groups[b].size();

        Node node1 = groups[a].get(0);
        Node node2 = groups[b].get(0);

        int aNum = map[node1.r][node1.c];
        int bNum = map[node2.r][node2.c];

        return (aSize + bSize) * aNum * bNum * cnt;
    }

    private static int bfs(int idx1, int idx2) {

        int cnt = 0; // idx1에 있는 원소들을 4방 탐색하여 접촉해있는 원소들을 만날 때마다 cnt++

        for (Node node1 : groups[idx1]) {
            for (Node node2 : groups[idx2]) {
                for (int i = 0; i < 4; i++) {
                    int nr = node1.r + rDir[i];
                    int nc = node1.c + cDir[i];

                    if (isInvalid(nr, nc)) continue;
                    if (nr == node2.r && nc == node2.c) cnt++;
                }
            }
        }

        return cnt;
    }

    private static void makeGroup(int r, int c, int idx) {

        visited[r][c] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        groups[idx].add(new Node(r, c));
        int num = map[r][c];

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + rDir[i];
                int nc = cur.c + cDir[i];
                if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] != num) continue;

                visited[nr][nc] = true;
                q.add(new Node(nr, nc));
                groups[idx].add(new Node(nr, nc));

            }

        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }

    private static void rotate() {

        int half = n / 2;
        int[][] temp = new int[n][n];

        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++)
                temp[i][j] = map[half - 1 - j][i];
        }

        for (int i = 0; i < half; i++) {
            for (int j = half + 1; j < n; j++){
                temp[i][j] = map[(n - 1) - j][i + (half + 1)];
            }
        }

        for (int i = half + 1; i < n; i++) {
            for (int j = 0; j < half; j++) {
                temp[i][j] = map[(n - 1) - j][i - (half + 1)];
            }
        }

        for (int i = half + 1; i < n; i++ ) {
            for (int j = half + 1; j < n; j++) {
                temp[i][j] = map[n - j + half][i];
            }
        }

        for (int i = 0; i < n; i++) {
            temp[half][i] = map[i][half];
        }
        for (int i = 0; i < n; i++) {
            temp[i][half] = map[half][n - 1 - i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = temp[i][j];
            }
        }

    }
}

