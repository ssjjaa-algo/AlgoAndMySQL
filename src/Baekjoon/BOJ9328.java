package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9328 {

    static int tc;
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static char[] keys;
    static boolean[] hasKey;
    static Set<Node> doors = new HashSet<>();
    static int ans = 0;
    static int[] rDirection = {0, -1, 0, 1};
    static int[] cDirection = {-1, 0, 1, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int x,y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            init();
            findCol(0);
            findCol(n - 1);
            findRow(0);
            findRow(m - 1);
            sb.append(ans).append("\n");
            ans = 0;
            doors.clear();
        }
        System.out.print(sb);
    }

    private static void findCol(int num) {

        for (int i = 0; i < m; i++) {

            if (visited[num][i]) continue;

            if (map[num][i] >= 'A' && map[num][i] <= 'Z' && !hasKey[map[num][i] - 'A'] ) {
                doors.add(new Node(num, i));
                visited[num][i] = true;
                continue;
            }

            if (map[num][i] == '.' || (map[num][i] >= 'A' && map[num][i] <= 'Z' && hasKey[map[num][i] - 'A'])) {
                map[num][i] = '.';
                bfs(num, i);
            }

            else if (map[num][i] >= 'a' && map[num][i] <= 'z') {
                hasKey[map[num][i] - 'a'] = true;
                map[num][i] = '.';
                bfs(num, i);
            }

            else if (map[num][i] == '$') {
                ans++;
                map[num][i] = '.';
                bfs(num, i);
            }

        }
    }

    private static void findRow(int num) {

        for (int i = 0; i < n; i++) {

            if (visited[i][num]) continue;

            if (map[i][num] >= 'A' && map[i][num] <= 'Z' && !hasKey[map[i][num] - 'A'] ) {
                doors.add(new Node(i, num));
                visited[i][num] = true;
                continue;
            }

            if (map[i][num] == '.' || (map[i][num] >= 'A' && map[i][num] <= 'Z' && hasKey[map[i][num] - 'A'])) {
                map[i][num] = '.';
                bfs(i, num);
            }

            else if (map[i][num] >= 'a' && map[i][num] <= 'z') {
                hasKey[map[i][num] - 'a'] = true;
                map[i][num] = '.';
                bfs(i, num);
            }

            else if (map[i][num] == '$') {
                ans++;
                map[i][num] = '.';
                bfs(i, num);
            }
        }

    }

    private static void bfs(int row, int col) {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(row, col));
        visited[row][col] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.x + rDirection[i];
                int nc = cur.y + cDirection[i];

                if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == '*') continue;

                if (map[nr][nc] == '.') {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }

                else if (map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {
                    hasKey[map[nr][nc] -'a'] = true;
                    map[nr][nc] = '.';
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }

                else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z' && hasKey[map[nr][nc] - 'A']) {
                    map[nr][nc] = '.';
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }

                else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z' && !hasKey[map[nr][nc] - 'A']) {
                    visited[nr][nc] = true;
                    doors.add(new Node(nr, nc));
                }

                else if (map[nr][nc] == '$') {
                    ans++;
                    visited[nr][nc] = true;
                    map[nr][nc] = '.';
                    q.add(new Node(nr, nc));
                }
            }

            List<Node> removeDoors = new ArrayList<>();
            for (Node node : doors) {

                if (hasKey[map[node.x][node.y] - 'A']) {
                    map[node.x][node.y] = '.';
                    q.add(new Node(node.x, node.y));
                    removeDoors.add(node);
                }
            }

            removeDoors.forEach(doors::remove);
        }

    }

    private static boolean isInvalid(int nr, int nc) {
        return nr < 0 || nr >= n || nc < 0 || nc >= m;
    }

    private static void init() throws IOException {

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new char[n][m];
        visited = new boolean[n][m];
        hasKey = new boolean[26];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        keys = br.readLine().toCharArray();

        if (keys[0] != '0') {
            for (char k : keys) {
                hasKey[k - 'a'] = true;
            }
        }
    }
}
