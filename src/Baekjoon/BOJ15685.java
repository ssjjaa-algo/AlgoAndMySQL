package Baekjoon;


import java.io.*;
import java.util.*;

public class BOJ15685 {

    static int[] xDirection = {1,0,-1,0};
    static int[] yDirection = {0,-1,0,1};
    static boolean[][] map = new boolean[101][101];
    public static void main(String[] args) throws IOException {

        input();
        System.out.println(calculate());
    }

    private static int calculate() {

        int sum = 0;
        for (int i = 0 ; i < 100; i++) {
            for (int j = 0; j < 100; j ++) {
                if (map[i][j]) {
                    sum = check(i,j) ? sum + 1 : sum;

                }
            }
        }

        return sum;
    }

    private static boolean check(int i, int j) {

        if (!map[i][j+1]|| !map[i+1][j]|| !map[i+1][j+1]) return false;

        return true;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input;

        int x,y,d,g;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            d = Integer.parseInt(input[2]);
            g = Integer.parseInt(input[3]);

            makeDragonCurve(x,y,d,g);
        }

    }
    private static void makeDragonCurve(int x, int y, int d, int g) {

        List<Integer> directions = new ArrayList<>();

        int size = 0;
        directions.add(d);
        for (int i = 1; i <= g; i++) {

            size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        size = directions.size();
        map[x][y] = true;
        for (int i = 0; i < size; i++) {
            x = x + xDirection[directions.get(i)];
            y = y + yDirection[directions.get(i)];
            map[x][y] = true;
        }
    }
}