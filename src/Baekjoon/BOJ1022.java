package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1022 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int r1 = Integer.parseInt(input[0]);
        int c1 = Integer.parseInt(input[1]);
        int r2 = Integer.parseInt(input[2]);
        int c2 = Integer.parseInt(input[3]);
        br.close();

        int[][] map = new int[r2 - r1 + 1][c2 - c1 + 1];
        calculate(r1,c1,r2,c2,map);

    }

    private static void calculate(int r1, int c1, int r2, int c2, int[][] map) {

        int[] rDirection = {0, -1, 0, 1};
        int[] cDirection = {1, 0, -1, 0};

        int r = 0;
        int c = 0;
        int cnt = 0;
        int num = 1;
        int directionCnt = 1;
        int dir = 0;
        int max = 0;
        // 모서리의 각 부분이 0이 아닌 경우 종료조건
        while(map[0][0] == 0 || map[r2 - r1][0] == 0 || map[r2 - r1][c2 - c1] == 0 || map[0][c2 - c1] == 0) {

            if (r >= r1 && r <= r2 && c >= c1 && c <= c2) {
                map[r - r1][c - c1] = num;
                max = Math.max(max, num);
            }
            cnt++;
            num++;
            r = r + rDirection[dir];
            c = c + cDirection[dir];

            if (cnt == directionCnt) {
                cnt = 0;
                if (dir == 1 || dir == 3) {
                    directionCnt++;
                }
                dir = (dir + 1) % 4;
            }
        }

        StringBuilder formatter = new StringBuilder("%");
        formatter.append(String.valueOf(max).length()).append("d ");
        String form = formatter.toString();

        for (int i = 0; i < r2 - r1 + 1; i++) {
            for (int j = 0; j < c2 - c1 + 1; j++) {
                System.out.printf(form, map[i][j]);
            }
            System.out.println();
        }
    }
}
