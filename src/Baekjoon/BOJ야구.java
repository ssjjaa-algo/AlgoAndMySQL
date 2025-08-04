package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ야구 {

    static int n;
    static int[][] inning;
    static int[] player = new int[9];
    static boolean[] selected = new boolean[9];
    static int res = 0;
    static int[] base = new int[4];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inning = new int[n][9];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                inning[i][j] = Integer.parseInt(input[j]);
            }
        }

        player[3] = 0;
        selected[3] = true;

        makePlayerSequence(1);

        System.out.println(res);
    }

    private static void makePlayerSequence(int cnt) {

        if (cnt == 9) {
            res = Math.max(res, baseball());
            Arrays.fill(base, 0);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            player[i] = cnt;
            makePlayerSequence(cnt + 1);
            selected[i] = false;
        }
    }

    private static int baseball() {

        int out = 0;
        int curInning = 0;
        int idx = 0;
        int scoreSum = 0;
        while (curInning < n) {

            while (out != 3) {

                int hit = inning[curInning][player[idx]];

                if (hit == 0) {
                    out++;
                }
                else {
                    scoreSum += playerHitInformation(hit);
                }

                idx = (idx + 1) % 9;
            }
            out = 0;
            curInning++;
            Arrays.fill(base, 0);
        }

        return scoreSum;

    }

    private static int playerHitInformation(int hit) {

        int score = 0;
        base[0] = 1;

        for (int i = 3; i >= 0; i--) {
            if (base[i] == 0) continue;
            int next = i + hit;
            if (next >= 4) {
                score++;
            } else {
                base[next] = 1;
            }
            base[i] = 0;
        }

        return score;
    }
}
