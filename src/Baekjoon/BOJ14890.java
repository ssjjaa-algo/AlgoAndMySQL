package Baekjoon;

import java.io.*;

public class BOJ14890 {

    static int[][] map;
    static int N,L;
    public static void main(String[] args) throws IOException{

        init();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (row(i)) cnt++;
            if (col(i)) cnt++;
        }
        System.out.println(cnt);

    }

    private static boolean row(int num) {

        int tempCnt = 1;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = map[num][i] - map[num][i+1];

            if (diff > 1 || diff < -1) return false;

            if (diff == 1) {
                if (i + L >= N) return false;
                int standard = map[num][i+1];
                for (int j = i + 1; j <= i + L; j++) {
                    if (visited[j] || standard != map[num][j]) return false;
                    visited[j] = true;
                }
            }

            else if (diff == -1) {
                if (i - L + 1 < 0) return false;
                int standard = map[num][i];
                for (int j = i - L + 1; j <= i; j++) {
                    if (visited[j] || standard != map[num][j]) return false;
                    visited[j] = true;
                }
            }
        }

        return true;

    }

    private static boolean col(int num) {

        boolean[] visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {

            int diff = map[i][num] - map[i+1][num];

            if (diff < -1 || diff > 1) return false;

            if (diff == 1) {
                if (i + L >= N) return false;
                int standard = map[i+1][num];
                for (int j = i + 1; j <= i + L; j++) {
                    if (visited[j] || standard != map[j][num]) return false;
                    visited[j] = true;
                }
            }

            else if (diff == -1) {
                if (i - L + 1 < 0) return false;
                int standard = map[i][num];
                for (int j = i - L + 1; j <= i; j++) {
                    if (visited[j] || standard != map[j][num]) return false;
                    visited[j] = true;
                }
            }
        }

        return true;
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
    }
}
