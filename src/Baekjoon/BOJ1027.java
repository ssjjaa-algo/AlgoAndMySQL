package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1027 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N + 1];

        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            buildings[i] = Integer.parseInt(input[i - 1]);
        }

        calculate(buildings, N);
    }

    private static void calculate(int[] buildings, int N) {

        int[] scores = new int[N + 1];

        for (int i = 1; i < N; i++) {
            double degree = Integer.MIN_VALUE;
            for (int j = i + 1; j <= N; j++) {

                double curDegree = (double)(buildings[j] - buildings[i]) / (j - i);
                if (curDegree > degree) {
                    degree = curDegree;
                    scores[i]++;
                    scores[j]++;
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, scores[i]);
        }
        System.out.println(max);

    }
}
