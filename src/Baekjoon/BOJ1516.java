package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1516 {

    static int N;
    static int[] level;
    static List<Integer> adj[];
    static int[] time;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (level[i] == 0) {
                dp[i] = time[i];
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {

                dp[next] = Math.max(dp[next], dp[cur] + time[next]);

                if (--level[next] == 0) {
                    q.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }

        System.out.print(sb);

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] input;
        level = new int[N + 1];
        adj = new ArrayList[N + 1];
        time = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");

            time[i] = Integer.parseInt(input[0]);
            int length = input.length;
            for (int j = 1; j < length; j++) {
                int num = Integer.parseInt(input[j]);
                if (num == -1) break;

                level[i]++;
                adj[num].add(i);
            }
        }
    }
}
