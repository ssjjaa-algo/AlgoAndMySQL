package CodeTree;

import java.io.*;
import java.util.*;

public class 꼬리잡기놀이 {

    static int n, m, k;
    static int[][] map;
    static int score = 0;
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Node[] leader;
    static int[] rDir = {0, -1, 0, 1};
    static int[] cDir = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        leader = new Node[m];

        map = new int[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 1) {
                    leader[cnt++] = new Node(i, j);
                }
            }
        }

        calculate();
    }

    private static void calculate() {

        int round = 0;
        int dir = -1;
        while (round < k) {

            move();
            if (round % n == 0) {
                dir = (dir + 1) % 4;
            }
            throwBall(dir, round);
            round++;
        }
        System.out.println(score);
    }

    private static void throwBall(int dir, int round) {

        if (dir == 0) {
            for (int i = 0; i < n; i++) {
                if (map[round % n][i] == 0 || map[round % n][i] == 4) continue;
                score(round % n, i);
                return;
            }
        }
        else if (dir == 1) {
            for (int i = n - 1; i >= 0; i--) {
                if (map[i][round % n] == 0 || map[i][round % n] == 4) continue;
                score(i, round % n);
                return;
            }
        }
        else if (dir == 2) {
            for (int i = n - 1; i >= 0; i--) {
                if (map[n - 1 - (round % n)][i] == 0 || map[n - 1 - (round % n)][i] == 4) continue;
                score((n - 1) - (round % n), i);
                return;
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if (map[i][n - 1 - (round % n)] == 0 || map[i][n - 1 - (round % n)] == 4) continue;
                score(i, n - 1 - (round % n));
                return;
            }
        }
    }

    private static void score(int r, int c) {

        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        Node first = null;
        Node last = null;
        q.add(new Node(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) { // 1번의 위치를 찾는다. 공을 획득한 팀이 몇 번째 순서인지 알기 위해 찾아야 한다.
            Node cur = q.poll();
            if (map[cur.r][cur.c] == 1 ) {
                first = new Node(cur.r, cur.c);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + rDir[i];
                int nc = cur.c + cDir[i];

                if (isInvalid(nr,nc) || visited[nr][nc] || map[nr][nc] == 0) continue; // 1, 2, 3의 경우에만 이동한다.

                visited[nr][nc] = true;
                q.add(new Node(nr, nc));
            }
        }

        q.clear();
        for (int i = 0; i < n; i++) Arrays.fill(visited[i], false);
        visited[first.r][first.c] = true;
        q.add(new Node(first.r, first.c));
        Queue<Node> sequence = getSequence(q, visited, first.r, first.c); // 순서를 가져와서

        for (Node node : sequence) { // 꼬리의 위치를 찾아내고
            if (map[node.r][node.c] == 3) {
                last = node;
                break;
            }
        }

        int cnt = 1;
        for (Node node : sequence) { // 공을 획득한 노드가 몇 번째에 있는지 확인하여 점수 주기
            if (node.r == r && node.c == c) {
                score += (cnt * cnt);
                break;
            }
            cnt++;
        }
        for (int i = 0; i < m; i++) { // 현재 리더가 몇 번째 팀인지 확인하여 리더의 위치를 꼬리 위치와 변경
            if (leader[i].r == first.r && leader[i].c == first.c) {
                leader[i].r = last.r;
                leader[i].c = last.c;
                break;
            }
        }
        // 리더와 꼬리의 위치를 맵에서 바꿔준다.
        map[last.r][last.c] = 1;
        map[first.r][first.c] = 3;

    }

    private static void move() {

        for (int i = 0; i < m; i++) {
            moveTeam(leader[i].r, leader[i].c, i);
        }
    }

    private static void moveTeam(int r, int c, int team) {

        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        visited[r][c] = true;
        q.add(new Node(r, c));
        Queue<Node> sequence = getSequence(q, visited, r, c); // 팀의 위치를 파악한다.

        while (sequence.size() != 1) { // 순서대로 이동시킨다.

            Node cur = sequence.poll();
            Node next = sequence.peek();
            map[cur.r][cur.c] = map[next.r][next.c];
        }

        Node cur = sequence.poll(); // 리더는 바로 다음 노드로 이동해야 하며, sequence 배열에서 마지막에 위치하고 있다.
        map[cur.r][cur.c] = 1;
        leader[team].r = cur.r;
        leader[team].c = cur.c;

    }

    private static Queue<Node> getSequence(Queue<Node> q, boolean[][] visited, int r, int c) { // 리더부터 순서대로 팀원 위치시키기

        Queue<Node> sequence = new ArrayDeque<>();
        sequence.add(new Node(r, c));

        while(!q.isEmpty()) {

            Node cur = q.poll();
            int num = map[cur.r][cur.c];
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + rDir[i];
                int nc = cur.c + cDir[i];

                if (isInvalid(nr, nc) || visited[nr][nc]) continue;

                if (map[nr][nc] == num || map[nr][nc] == num + 1) { // 1을 기준으로 시작하므로, 리더 입장에서 3과 4로 점프하지 않는다.
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                    sequence.add(new Node(nr, nc));
                    break;
                }
            }
        }

        return sequence;

    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}

