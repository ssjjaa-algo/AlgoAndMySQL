package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14499 {

    static int[][] map;

    static int[] dice;
    static int[] copyDice;
    static int N,M,x,y,K;
    static int[] commands;
    static int[] xDirection = {0,0,-1,1};
    static int[] yDirection = {1,-1,0,0};

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static String calculate() {

        int command = 0;
        int nx,ny;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            command = commands[i];
            nx = x + xDirection[command - 1];
            ny = y + yDirection[command - 1];

            if (isInvalid(nx,ny)) continue;

            x = nx;
            y = ny;
            copyDice = dice.clone();

            switch(command) {
                case 1: {
                    right();
                    break;
                }

                case 2: {
                    left();
                    break;
                }

                case 3: {
                    up();
                    break;
                }

                case 4: {
                    down();
                    break;
                }
            }

            compareFloorAndDice();
            sb.append(dice[0]).append("\n");

        }
        return sb.toString();
    }

    private static void compareFloorAndDice() {

        if (map[x][y] == 0) {
            map[x][y] = dice[5];
        }
        else {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }

    private static void down() {
        dice[0] = copyDice[1];
        dice[1] = copyDice[5];
        dice[4] = copyDice[0];
        dice[5] = copyDice[4];
    }

    private static void up() {
        dice[0] = copyDice[4];
        dice[1] = copyDice[0];
        dice[4] = copyDice[5];
        dice[5] = copyDice[1];
    }

    private static void left() {
        dice[0] = copyDice[2];
        dice[2] = copyDice[5];
        dice[3] = copyDice[0];
        dice[5] = copyDice[3];
    }

    private static void right() {
        dice[0] = copyDice[3];
        dice[2] = copyDice[0];
        dice[3] = copyDice[5];
        dice[5] = copyDice[2];
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        x = Integer.parseInt(input[2]);
        y = Integer.parseInt(input[3]);
        K = Integer.parseInt(input[4]);

        map = new int[N][M];
        commands = new int[K];
        dice = new int[6];
        copyDice = new int[6];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = br.readLine().split(" ");

        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(input[i]);
        }
    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= M;
    }
}
