package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ14267 {

    static int n, m;
    static List<Integer>[] adj;
    static int[] score;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        adj = new ArrayList[n + 1];
        score = new int[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        input = br.readLine().split(" ");
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(input[i - 1]);
            adj[parent].add(i);
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int target = Integer.parseInt(input[0]);
            int point = Integer.parseInt(input[1]);
            score[target] += point;
        }

        br.close();
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int parent) {

        for (int child : adj[parent]) {

            score[child] += score[parent];
            dfs(child);
        }
    }
}
