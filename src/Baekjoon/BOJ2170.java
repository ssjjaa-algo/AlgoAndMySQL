package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2170 {

    static int N;
    static int line[][];
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int distance = line[0][1] - line[0][0];
        int end = line[0][1];
        for (int i = 1; i < N; i++) {

            if (line[i][1] <= end) continue;

            else if (line[i][0] <= end) {
                distance += (line[i][1] - end);
            }
            else if (line[i][0] > end) {
                distance += (line[i][1] - line[i][0]);
            }
            end = line[i][1];
        }

        System.out.println(distance);

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        line = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            line[i][0] = Integer.parseInt(input[0]);
            line[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(line, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
        }
            return Integer.compare(o1[0], o2[0]);
        });

    }
}
