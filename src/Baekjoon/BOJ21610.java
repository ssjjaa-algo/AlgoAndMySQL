package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ21610 {

    static int N,M;
    static int[][] map;
    static List<Node> cloud = new ArrayList<>();
    static Command[] command;
    static int[] xDirection = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] yDirection = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] xDiagonal = {-1,-1,1,1};
    static int[] yDiagonal = {-1,1,1,-1};
    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static class Command {
        int dir;
        int move;

        public Command(int dir, int move) {
            this.dir = dir;
            this.move = move;
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static int calculate() {

        for (int i = 0; i < M; i++) {
            boolean[][] hasCloud = new boolean[N][N];
            moveCloud(command[i],hasCloud);
            rain();
            copy();
            makeCloud(hasCloud);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += map[i][j];
            }
        }

        return ans;
    }

    private static void makeCloud(boolean[][] hasCloud) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !hasCloud[i][j]) {
                    map[i][j] -= 2;
                    cloud.add(new Node(i,j));
                }
            }
        }
    }

    private static void copy() {
        for (Node temp : cloud) {

            int cnt = 0;

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + xDiagonal[i];
                int ny = temp.y + yDiagonal[i];

                if (isInvalid(nx,ny)) continue;

                if (map[nx][ny] > 0) cnt++;
            }

            map[temp.x][temp.y] += cnt;
        }

        cloud.clear();
    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }

    private static void rain() {

        for (Node temp : cloud) {
            map[temp.x][temp.y] += 1;
        }
    }

    private static void moveCloud(Command command, boolean[][] hasCloud) {

        for (Node temp : cloud) {
            temp.x += (xDirection[command.dir] * (command.move % N));
            temp.y += (yDirection[command.dir] * (command.move % N));

            if (temp.x < 0) {
                temp.x = N + temp.x;
            }
            else if (temp.x >= N) {
                temp.x -= N;
            }

            if (temp.y < 0) {
                temp.y = N + temp.y;
            }
            else if (temp.y >= N) {
                temp.y -= N;
            }

            hasCloud[temp.x][temp.y] = true;
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][N];
        command = new Command[M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        cloud.add(new Node(N - 1,0));
        cloud.add(new Node(N - 1,1));
        cloud.add(new Node(N - 2,0));
        cloud.add(new Node(N - 2,1));

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            command[i] = new Command(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

    }

}