package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14891 {

    static char[][] clock;
    static int K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static int calculate() throws IOException{

        String[] input;
        int numOfClock, rotate;
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            numOfClock = Integer.parseInt(input[0]);
            rotate = Integer.parseInt(input[1]);

            dfs(numOfClock,rotate,-1);
            dfs(numOfClock,rotate,1);
            rotation(numOfClock,rotate);
        }

        return getSum();
    }

    private static int getSum() {

        int[] score = {0,1,2,4,8};

        int res = 0;
        for (int i = 1; i <= 4; i++) {
            if (clock[i][0] == '1') {
                res += score[i];
            }
        }

        return res;
    }

    private static void dfs(int numOfClock, int rotate, int direction) {

        int next = numOfClock + direction;

        if (next < 1 || next > 4) return;

        if (direction == -1) {
            if (clock[next][2] == clock[numOfClock][6]) return;
        }

        else if (direction == 1) {
            if (clock[next][6] == clock[numOfClock][2]) return;
        }

        dfs(next, -rotate, direction);
        rotation(next,-rotate);

    }

    private static void rotation(int numOfClock, int rotate) {

        if (rotate == 1) {
            char temp = clock[numOfClock][7];

            for (int i = 7; i > 0; i--) {
                clock[numOfClock][i] = clock[numOfClock][i-1];
            }
            clock[numOfClock][0] = temp;
        }

        else {
            char temp = clock[numOfClock][0];

            for (int i = 0; i < 7; i++) {
                clock[numOfClock][i] = clock[numOfClock][i + 1];
            }
            clock[numOfClock][7] = temp;
        }
    }

    private static void init() throws IOException {

        clock = new char[5][8];

        for (int i = 1; i <= 4; i++) {
            clock[i] = br.readLine().toCharArray();
        }

        K = Integer.parseInt(br.readLine());

    }
}
