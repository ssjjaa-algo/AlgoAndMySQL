package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ1516Re {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] levels = new int[N + 1];
        int[] times = new int[N + 1];
        List<Integer>[] child = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            times[i] = Integer.parseInt(input[0]);

            for (int j = 1; j < input.length; j++) {

                int next = Integer.parseInt(input[j]);
                if (next == -1) break;

                levels[i]++;
                child[next].add(i);
            }
        }

        calculate(N, levels, times, child);
    }

    private static void calculate(int N, int[] levels, int[] times, List<Integer>[] child) {

        Queue<Integer> q = new ArrayDeque<>();
        int[] results = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (levels[i] == 0) q.add(i);
            results[i] = times[i];
        }

        while (!q.isEmpty()) {

            int cur = q.poll();

            for (int next : child[cur]) {
                levels[next]--;
                results[next] = Math.max(results[next], results[cur] + times[next]);
                if (levels[next] == 0) {
                    q.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            sb.append(results[i]).append("\n");
        }

        System.out.print(sb);
    }
}
