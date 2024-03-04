package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1766 {

    static List<Integer> adj[];
    static int[] level;
    static int N, M;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (level[i] == 0) q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {

            int cur = q.poll();
            sb.append(cur).append(" ");

            for (int next : adj[cur]) {
                level[next]--;
                if (level[next] == 0) q.add(next);
            }
        }

        System.out.println(sb);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        level = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int prev, next;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            prev = Integer.parseInt(input[0]);
            next = Integer.parseInt(input[1]);
            adj[prev].add(next);
            level[next]++;
        }
    }
}
