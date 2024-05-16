package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1135 {

    static List<Integer>[] adj;
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        dp = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(input[i]);
            adj[num].add(i);
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int parent) {

        // 가장 오래 걸리는 녀석부터 순번을 매겨서 시간 주기.
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for (int next : adj[parent]) {
            dp[next] = dfs(next);
            list.add(dp[next]);
        }

        list.sort(Collections.reverseOrder());
        int cnt = 0;
        for (int next : list) {
            max = Math.max(max, next + ++cnt);
        }

        return max;
    }
}

