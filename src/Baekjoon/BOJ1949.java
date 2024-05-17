package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1949 {

    static List<Integer>[] adj;
    static int[] town;
    static int[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        town = new int[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            town[i] = Integer.parseInt(input[i - 1]);
        }

        for (int i = 0; i < N - 1; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            adj[a].add(b);
            adj[b].add(a);
        }

        br.close();

        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur, int parent) {

        for (int child : adj[cur]) {

            if (child == parent) continue;

            dfs(child, cur);
            dp[cur][0] += Math.max(dp[child][1], dp[child][0]);
            dp[cur][1] += dp[child][0];
        }

        dp[cur][1] += town[cur];
    }
}
