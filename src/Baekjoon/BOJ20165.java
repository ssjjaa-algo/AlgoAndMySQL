package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20165 {

    static class Node {
        int height;
        char alive;

        public Node(int height, char alive) {
            this.height=height;
            this.alive=alive;
        }
    }
    static class Command {
        int x, y, direction;

        public Command(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static int N, M, R;
    static Node[][] map;
    static Command[] command;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        int idx = 0;
        int ans = 0;
        while (R-- > 0) {

            ans += attack(idx * 2);
            defend(idx * 2 + 1);
            idx++;
        }

        System.out.println(ans);
        print();
    }

    private static void print() {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(map[i][j].alive).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void defend(int idx) {

        int x = command[idx].x;
        int y = command[idx].y;
        map[x][y].alive='S';

    }

    private static int attack(int idx) {

        int x = command[idx].x;
        int y = command[idx].y;
        int direction = command[idx].direction;

        char alive = map[x][y].alive;
        int height = map[x][y].height;
        if (alive == 'F') return 0;

        int cnt = 1;
        map[x][y].alive = 'F';

        for (int i = height -1; i > 0; i--) {
            x += xDirection[direction];
            y += yDirection[direction];

            if (isInvalid(x, y)) break;

            if (map[x][y].alive == 'F') continue;

            if (map[x][y].height > i) {
                i = map[x][y].height;
            }

            map[x][y].alive = 'F';
            cnt++;

        }

        return cnt;
    }

    private static boolean isInvalid(int x, int y) {
        return x < 1 || x > N || y < 1 || y > M;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        map = new Node[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = new Node(Integer.parseInt(input[j - 1]),'S');
            }
        }

        command = new Command[R * 2];

        for (int i = 0; i < R; i++) {
            input = br.readLine().split(" ");
            switch(input[2]) {

                case "E" : {
                    input[2] = "1";
                    break;
                }

                case "W" : {
                    input[2] = "3";
                    break;
                }

                case "S" : {
                    input[2] = "2";
                    break;
                }

                case "N" : {
                    input[2] = "0";
                    break;
                }
            }

            command[i * 2] = new Command(Integer.parseInt(input[0]),
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]));

            input = br.readLine().split(" ");
            command[i * 2 + 1] = new Command(Integer.parseInt(input[0]),
                    Integer.parseInt(input[1]),
                    -1);

        }

    }
}
