package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1063 {

    static int command;
    static String[] commands;
    static char[] king;
    static char[] stone;
    static String[] dirCommand = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    public static void main(String[] args) throws IOException{

        init();
        calculate();
    }

    private static void calculate() {

        Map<String, int[]> direction = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            direction.put(dirCommand[i], dir[i]);
        }

        for (String c : commands) {

            int[] d = direction.get(c);
            int nr = king[1] + d[1];
            int nc = king[0] + d[0];
            if (isInvalid(nr, nc)) continue;

            else if (stone[0] == nc && stone[1] == nr) {

                if (isInvalid(nr + d[1], nc + d[0])) continue;

                stone[0] += d[0];
                stone[1] += d[1];
                king[0] += d[0];
                king[1] += d[1];

            }
            else {
                king[0] += d[0];
                king[1] += d[1];
            }
        }

        System.out.println(String.valueOf(king));
        System.out.println(String.valueOf(stone));
    }


    private static boolean isInvalid(int nr, int nc) {

        return (nr <= '0' || nr >= '9' || nc < 'A' || nc > 'H');
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        king = input[0].toCharArray();
        stone = input[1].toCharArray();
        command = Integer.parseInt(input[2]);
        commands = new String[command];

        for (int i = 0; i < command; i++) {
            commands[i] = br.readLine();
        }


    }
}
