package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2437 {

    static int N;
    static int[] map;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        /**
         * 누적합을 이용한 그리디 풀이.
         * 범위에 따라 dp는 불가능
         */
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + 1 < map[i]) break;

            sum += map[i];
        }

        System.out.println(sum + 1);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(map);
    }
}
