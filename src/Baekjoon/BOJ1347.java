package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1347 {

    /**
     * 입력으로 들어오는 좌표는 항상 이동이 가능한 좌표이다
     *
     * 1. 사이즈가 * 2인 배열을 선언해서 중심점을 기준으로 돌린다.
     *
     * 2. minR, maxR, minC, maxC 값을 누적으로 계산하여 배열 범위 축소
     *
     * 3. 출력
     */
    static int N;
    static char[] cmd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cmd = br.readLine().toCharArray();

        calculate();

    }

    private static void calculate() {

        int[] rDirection = {-1, 0, 1, 0};
        int[] cDirection = {0, 1, 0, -1};
        boolean[][] visited = new boolean[101][101];

        visited[50][50] = true;
        int row = 50;
        int col = 50;

        int minR = 50;
        int maxR = 50;
        int minC = 50;
        int maxC = 50;

        int direction = 2;

        for (char c : cmd) {
            if (c == 'L') {
                direction--;
                if (direction == -1) direction = 3;
            }
            else if (c == 'R') {
                direction++;
                if (direction == 4) direction = 0;
            }
            else {
                row += rDirection[direction];
                col += cDirection[direction];
                visited[row][col] = true;
                minR = Math.min(minR, row);
                minC = Math.min(minC, col);
                maxR = Math.max(maxR, row);
                maxC = Math.max(maxC, col);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                if (visited[i][j]) sb.append('.');
                else sb.append('#');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
