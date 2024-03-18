package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2533 {

    static int N;
    static List<Integer> adj[];
    static int[][] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur) {

        dp[cur][0] = 0;
        dp[cur][1] = 1;
        visited[cur] = true;

        for (int next : adj[cur]) {

            if (visited[next]) continue;
            dfs(next);
            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            adj[a].add(b);
            adj[b].add(a);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
    }
}
