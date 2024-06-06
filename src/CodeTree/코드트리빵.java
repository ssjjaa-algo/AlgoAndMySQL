package CodeTree;

import java.io.*;
import java.util.*;

public class 코드트리빵 {

    static int n, m;
    static int[][] map;
    static int[] rDir = {-1, 0, 0, 1};
    static int[] cDir = {0, -1, 1, 0};

    static class Node implements Comparable<Node>{
        int r, c, d;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {

            if (this.d == o.d) {
                if (this.r == o.r) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.d, o.d);
        }
    }

    static Node[] store;
    static Node[] people;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n + 1][n + 1];
        store = new Node[m + 1];
        people = new Node[m + 1];

        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");
            store[i] = new Node(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        calculate();
    }

    private static void calculate() {

        int time = 1;

        while(true) {

            moveToStore(time);
            if (time <= m) {
                moveToBaseCamp(time);
            }
            if(allInStore()) break;
            time++;
        }

        System.out.println(time);
    }

    private static boolean allInStore() {

        for (int i = 1; i <= m; i++) {
            if (map[store[i].r][store[i].c] != 2) return false;
        }

        return true;
    }

    private static void moveToBaseCamp(int time) {

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(store[time].r, store[time].c, 0));
        boolean[][] visited = new boolean[n + 1][n + 1];
        visited[store[time].r][store[time].c] = true;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (map[cur.r][cur.c] == 1) {
                    people[time] = cur;
                    map[cur.r][cur.c] = 2;
                    return;
                }
                for (int j = 0; j < 4; j++) {

                    int nr = cur.r + rDir[j];
                    int nc = cur.c + cDir[j];

                    if (isInvalid(nr, nc) || map[nr][nc] == 2 || visited[nr][nc]) continue;

                    q.add(new Node(nr ,nc, cur.d + 1));
                    visited[nr][nc] = true;
                }
            }
        }

    }

    private static void moveToStore(int time) {

        for (int i = 1; i < time && i <= m; i++) {

            if (people[i].r == store[i].r && people[i].c == store[i].c) continue;
            int dir = bfs(people[i].r, people[i].c, i);

            if (dir == -1) continue;

            people[i].r += rDir[dir];
            people[i].c += cDir[dir];
        }

        for (int i = 1; i < time && i <= m; i++) {
            if (people[i].r == store[i].r && people[i].c == store[i].c) {
                map[store[i].r][store[i].c] = 2;
            }
        }
    }

    private static int bfs(int r, int c, int time) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + rDir[i];
            int nc = c + cDir[i];
            if (isInvalid(nr ,nc) || visited[nr][nc] || map[nr][nc] == 2) continue;
            q.add(new int[]{nr, nc, i});
        }

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] cur = q.poll();
                if (cur[0] == store[time].r && cur[1] == store[time].c) {
                    return cur[2];
                }
                for (int j = 0; j < 4; j++) {

                    int nr = cur[0] + rDir[j];
                    int nc = cur[1] + cDir[j];
                    if (isInvalid(nr, nc) || map[nr][nc] == 2 || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cur[2]});
                }
            }
        }

        return 0;

    }

    private static boolean isInvalid(int r, int c) {

        return r < 1 || r > n || c < 1 || c > n;
    }

}
