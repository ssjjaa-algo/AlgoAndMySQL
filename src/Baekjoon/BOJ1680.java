package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1680 {

    static class Info {
        int distance, garbage;

        public Info(int distance, int garbage) {
            this.distance = distance;
            this.garbage = garbage;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int W = Integer.parseInt(input[0]);
            int N = Integer.parseInt(input[1]);

            Info[] garbages = new Info[N];
            for (int j = 0; j < N; j++) {
                input = br.readLine().split(" ");
                int distance = Integer.parseInt(input[0]);
                int garbage = Integer.parseInt(input[1]);
                garbages[j] = new Info(distance, garbage);
            }

            sb.append(calculate(garbages, N, W)).append("\n");
        }

        System.out.println(sb);
    }

    private static int calculate(Info[] garbages, int N, int W) {

        int distance = 0;

        int d = 0;
        int prev = 0;
        int weight = 0;
        for (int i = 0; i < N; i++) {

            weight += garbages[i].garbage;
            d += garbages[i].distance - prev;
            prev = d;

            if (weight == W) {
                distance += (d * 2);
                d = 0;
                prev = 0;
                weight = 0;
            }
            else if (weight > W) {
                distance += (d * 2);
                weight = garbages[i].garbage;
                d = garbages[i].distance;
                prev = d;
            }
        }
        distance += (d * 2);

        return distance;
    }
}
