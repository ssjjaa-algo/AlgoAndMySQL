package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ17822 {

    static int N, M, T;
    static int[][] map;
    static Command[] commands;
    static int[] direction = {1, -1};
    static int[] xDirection = {-1, 0, 1, 0};
    static int[] yDirection = {0, 1, 0, -1};
    static class Command {
        int x, d, k;
        public Command(int x, int d, int k) {
            this.x = x;
            this.d = d;
            this.k = k;
        }
    }

    static class Node {
        int x, y, value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y =y;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        for (Command command : commands) {
            rotate(command);
            findAdjacentNumbers();
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) continue;
                sum += map[i][j];
            }
        }

        System.out.println(sum);

    }

    private static void findAdjacentNumbers() {

        boolean[][] visited = new boolean[N + 1][M];
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || map[i][j] == -1) continue;
                if(bfs(i, j, visited)) flag = true;
            }
        }

        if (!flag) {
            changeNumbersFromAverage();
        }
    }

    private static boolean bfs(int row, int col, boolean[][] visited) {

        Queue<Node> q = new ArrayDeque<>();
        visited[row][col] = true;
        q.add(new Node(row, col, map[row][col]));

        boolean flag = false;
        while (!q.isEmpty()) {

            Node temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = temp.x + xDirection[i];
                int nc = temp.y + yDirection[i];

                if (nr <= 0 || nr >= N + 1) continue;

                if (nc == M) nc = 0;
                else if (nc == -1) nc = M - 1;

                if (visited[nr][nc] || map[nr][nc] != temp.value) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr, nc, temp.value));
                map[nr][nc] = -1;
                map[temp.x][temp.y] = -1;
                flag = true;
            }
        }

        return flag;
    }


    private static void changeNumbersFromAverage() {

        double sum = 0;
        double cnt = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) continue;
                cnt++;
                sum += map[i][j];
            }
        }

        double average = sum / cnt;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) continue;

                if (map[i][j] > average) map[i][j]--;
                else if (map[i][j] < average) map[i][j]++;
            }
        }
    }

    private static void rotate(Command command) {

        int target = command.x;

        while (target <= N) {

            rotateTarget(target, command);
            target += command.x;
        }
    }

    private static void rotateTarget(int target, Command command) {

        int d = direction[command.d];

        if (d == 1) {
            for (int k = 0; k < command.k; k++) {
                int last = map[target][M - 1];
                for (int i = M - 1; i > 0; i--) {
                    map[target][i] = map[target][i - 1];
                }
                map[target][0] = last;
            }
        }
        else {
            for (int k = 0; k < command.k; k++) {
                int temp = map[target][0];
                for (int i = 0; i < M - 1; i++) {
                    map[target][i] = map[target][i + 1];
                }
                map[target][M - 1] = temp;
            }
        }

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        map = new int[N + 1][M];
        commands = new Command[T];

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int x, d, k;
        for (int i = 0; i < T; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            d = Integer.parseInt(input[1]);
            k = Integer.parseInt(input[2]);
            commands[i] = new Command(x, d, k);
        }
    }
}
