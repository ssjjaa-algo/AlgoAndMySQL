package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1049 {

    static int N, M;
    static int minPack = Integer.MAX_VALUE;
    static int minEach = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        if (minEach * 6 < minPack) {
            System.out.println(minEach * N);
            return;
        }

        int buyPack = N / 6;
        int remain = N % 6;

        int cost = buyPack * minPack;
        int remainCost = Math.min(remain * minEach, minPack);

        System.out.println(cost + remainCost);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            minPack = Math.min(minPack, Integer.parseInt(input[0]));
            minEach = Math.min(minEach, Integer.parseInt(input[1]));
        }

    }

}
