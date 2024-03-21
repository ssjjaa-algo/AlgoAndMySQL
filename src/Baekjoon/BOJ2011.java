package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {

    static char[] map;
    static int[] dp;
    static final int mod = 1000000;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        if (map[1] == '0') {
            System.out.println(0);
            return;
        }

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < map.length; i++) {

            // 1. i번째 위치의 숫자가 0
            if (map[i] == '0') {

                if (map[i - 1] == '1' || map[i - 1] == '2') {
                    dp[i] = dp[i - 2];
                }
                else {
                    System.out.println(0);
                    return;
                }
            }
            else {
                if (map[i - 1] == '1' || (map[i - 1] == '2' && (map[i] >= '1' && map[i] <= '6'))) {
                    dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
                }
                else {
                    dp[i] = dp[i - 1];
                }
            }

        }

        System.out.println(dp[map.length - 1]);

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        map = new char[input.length() + 1];
        map[0] = '0';
        for (int i = 1; i < map.length; i++) {
            map[i] = input.charAt(i - 1);
        }

        dp = new int[map.length];
    }
}
