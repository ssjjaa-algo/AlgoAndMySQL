package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ1389 {

    static int N, M;
    static List<Integer> adj[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            adj[a].add(b);
            adj[b].add(a);
        }

        int res = Integer.MAX_VALUE;
        int minPerson = 0;
        for (int i = 1; i <= N; i++) {

            int cnt = bfs(i);
            if (res > cnt) {
                res = cnt;
                minPerson = i;
            }

        }

        System.out.println(minPerson);
    }

    private static int bfs(int me) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(me);
        visited[me] = true;
        int[][] ans = new int[N + 1][N + 1];

        int cnt = 1;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int cur = q.poll();
                for (int friend : adj[cur]) {
                    if (visited[friend]) continue;

                    visited[friend] = true;
                    ans[me][friend] = cnt;
                    q.add(friend);
                }
            }
            cnt++;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            res += ans[me][i];
        }

        return res;
    }
}
