package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ20056 {

    static class FireBall implements Cloneable{
        int m,s,d;

        public FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }

    static List<FireBall>[][][] fireBall;
    static int N,M,K;
    static int[] xDirection = {-1,-1,0,1,1,1,0,-1};
    static int[] yDirection = {0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        while (K-- > 0) {

            first();
            second();

        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (FireBall ball : fireBall[i][j][0]) {
                    ans += ball.m;
                }
            }
        }

        System.out.println(ans);
    }

    private static void second() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (fireBall[i][j][0].size() >= 2) {
                    int size = fireBall[i][j][0].size();
                    combine(i,j);
                    divide(i,j,size);
                }
            }
        }
    }

    private static void divide(int x, int y, int size) {

        FireBall ball = fireBall[x][y][0].remove(0);

        int m = ball.m;
        int s = ball.s;
        int d = ball.d;

        int newM = m / 5;
        int newS = s / size;
        if (newM == 0) return;

        for (int i = 0; i < 4; i++) {
            fireBall[x][y][0].add(new FireBall(newM,newS,d));
            d += 2;
        }
    }

    private static void combine(int x, int y) {
        boolean odd = false;
        boolean even = false;

        int m = 0, s = 0, d = 0;

        for (FireBall ball : fireBall[x][y][0]) {
            m += ball.m;
            s += ball.s;

            if (ball.d % 2 == 1) {
                odd = true;
            }
            else {
                even = true;
            }
        }

        if (odd && even) {
            d = 1;
        }

        fireBall[x][y][0].clear();
        fireBall[x][y][0].add(new FireBall(m,s,d));


    }

    private static void first() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                move(i,j);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                int size = fireBall[i][j][1].size();
                for (int k = size - 1; k >= 0; k--) {
                    fireBall[i][j][0].add(fireBall[i][j][1].remove(k));
                }

            }
        }

    }

    private static void move(int x, int y) {

        int size = fireBall[x][y][0].size();

        for (int i = size - 1; i >= 0; i--) {

            FireBall ball = fireBall[x][y][0].remove(i);
            int nx = x + (ball.s % N) * xDirection[ball.d];
            int ny = y + (ball.s % N) * yDirection[ball.d];

            if (nx > N) {
                nx = nx - N;
            }
            else if (nx <= 0) {
                nx = N + nx;
            }

            if (ny > N) {
                ny = ny - N;
            }
            else if (ny <= 0) {
                ny = N + ny;
            }

            fireBall[nx][ny][1].add(new FireBall(ball.m, ball.s, ball.d));
        }

    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        fireBall = new ArrayList[N + 1][N + 1][2];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                for (int k = 0; k < 2; k++)
                    fireBall[i][j][k] = new ArrayList<>();

        int r, c, m, s, d;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);
            m = Integer.parseInt(input[2]);
            s = Integer.parseInt(input[3]);
            d = Integer.parseInt(input[4]);

            fireBall[r][c][0].add(new FireBall(m,s,d));
        }
    }
}
