package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1339 {

    static int N;
    static String[] str;
    static int[] weight = new int[26];

    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        for (String s : str) {
            int len = s.length();
            for (int i = 0; i < len; i++) {
                weight[s.charAt(i) - 'A'] += (int) Math.pow(10, len - i - 1);
            }
        }
        Arrays.sort(weight);

        int ans = 0;
        int value = 9;
        for (int i = 25; i > 15; i--) {
            ans += weight[i] * (value--);
        }
        System.out.println(ans);

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = new String[N];

        for (int i = 0 ; i < N; i++) {
            str[i] = br.readLine();
        }

    }
}
