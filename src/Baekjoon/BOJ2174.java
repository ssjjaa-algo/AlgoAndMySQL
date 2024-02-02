package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2174 {

    static class Robot {
        int x, y, direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static class Command {
        int robot, count;
        char command;

        public Command(int robot, char command, int count) {
            this.robot = robot;
            this.command = command;
            this.count = count;
        }
    }

    static Robot[] robots;
    static Command[] commands;
    static int[][] map;
    static int A, B, N, M;
    static int[] xDirection = { 1,0,-1,0};
    static int[] yDirection = {0,1,0,-1};

    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        for (Command command : commands) {

            int robot = command.robot;
            int count = command.count;

            if (command.command == 'L') {
                left(robot, count);
            }
            else if (command.command == 'R') {
                right(robot, count);
            }
            else {
                String mv = move(robot,count);

                if (!mv.equals("OK")) {
                    System.out.println(mv);
                    return;
                }
            }
        }

        System.out.println("OK");
    }

    private static void left(int num, int count) {

        int direction = robots[num].direction;

        while (count-- > 0) {
            direction -= 1;

            if (direction < 0) direction = 3;
        }

        robots[num].direction = direction;
        return;

    }

    private static void right(int num, int count) {
        int direction = robots[num].direction;

        while (count-- > 0) {
            direction += 1;

            if (direction > 3) direction = 0;
        }

        robots[num].direction = direction;
        return;
    }

    private static String move(int num, int count) {

        int x = robots[num].x;
        int y = robots[num].y;
        int direction = robots[num].direction;

        while (count-- > 0) {
            x += xDirection[direction];
            y += yDirection[direction];

            if (isInvalid(x,y)) return "Robot " + num + " crashes into the wall";

            if (isCrashed(x,y)) return "Robot " + num + " crashes into robot " + map[x][y];
        }

        map[robots[num].x][robots[num].y] = 0;
        map[x][y] = num;
        robots[num].x = x;
        robots[num].y = y;

        return "OK";
    }

    private static boolean isInvalid(int x, int y) {
        return x < 1 || x > B || y < 1 || y > A;
    }

    private static boolean isCrashed(int x, int y) {

        return map[x][y] != 0;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        map = new int[B + 1][A + 1];

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        robots = new Robot[N + 1];
        commands = new Command[M];

        int x, y;
        int direction;
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            direction = setDirection(input[2].charAt(0));

            robots[i] = new Robot(y,x,direction);
            map[y][x] = i;
        }

        int robot, count;
        char command;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            robot = Integer.parseInt(input[0]);
            command = input[1].charAt(0);
            count = Integer.parseInt(input[2]);

            commands[i] = new Command(robot, command, count);
        }

    }

    private static int setDirection(char c) {

        if (c == 'N') {
            return 0;
        }
        else if (c == 'E') {
            return 1;
        }
        else if (c == 'S') {
            return 2;
        }
        else {
            return 3;
        }
    }
}
