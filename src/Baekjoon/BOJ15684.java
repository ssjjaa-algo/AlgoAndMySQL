package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15684 {

    static int N,M,H;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        for (int i = 0; i <= 3; i++) {
            if (dfs(1,i,0)) return i;
        }

        return -1;
    }

    private static boolean dfs(int row, int limit, int count) {

        if (check()) return true;

        if (limit == count) return check();

        for (int i = row; i <= H; i++) {
            for (int j = 1; j < N; j++) {

                if (map[i][j - 1] || map[i][j] || map[i][j + 1]) continue;
                map[i][j] = true;
                if(dfs(row, limit, count + 1)) return true;
                map[i][j] = false;
            }
        }

        return false;
    }

    private static boolean check() {

        for (int i = 1; i <= N; i++) {

            int row = 1;
            int col = i;
            for (int j = 0; j < H; j++) {

                if (map[row][col]) {
                    col++;
                }
                else if (map[row][col - 1]) {
                    col--;
                }
                row++;
            }

            if (col != i) return false;
        }

        return true;
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);

        map = new boolean[H + 1][N + 1];

        int a, b;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            map[a][b] = true;
        }
    }
}
