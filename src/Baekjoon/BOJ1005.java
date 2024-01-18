package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ1005 {

    static List<Integer> adj[];
    static int[] cost;
    static int[] dp;
    static int[] level;
    static int N,K,W;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        calculate();

    }

    private static void calculate() throws IOException {

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            init();
            sb.append(topology()).append("\n");
        }

        System.out.print(sb);
    }

    private static int topology() {

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (level[i] == 0) {
                q.add(i);
                dp[i] = cost[i];
            }
        }

        while (!q.isEmpty()) {

            int parent = q.poll();
            for (int child : adj[parent]) {
                dp[child] = Math.max(dp[child], dp[parent] + cost[child]);
                if (--level[child] == 0) q.add(child);
            }
        }

        return dp[W];
    }

    private static void init() throws IOException {

        String[] input;
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        cost = new int[N + 1];
        dp = new int[N + 1];
        level = new int[N + 1];

        input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(input[i - 1]);
        }

        int prev, next;
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            prev = Integer.parseInt(input[0]);
            next = Integer.parseInt(input[1]);
            adj[prev].add(next);
            level[next]++;
        }

        W = Integer.parseInt(br.readLine());
    }

}
