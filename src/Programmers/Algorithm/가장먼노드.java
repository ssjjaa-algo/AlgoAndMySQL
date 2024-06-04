package Programmers.Algorithm;

import java.util.*;

class 가장먼노드 {

    static List<Integer> adj[];
    public int solution(int n, int[][] edge) {

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        return bfs(1, n);
    }

    public int bfs(int start, int n) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        q.add(new int[]{1, 0});

        int ans = 0;
        int len = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int node = cur[0];
                int depth = cur[1];

                if (len == depth) {
                    ans++;
                }
                else if (depth > len) {
                    len = depth;
                    ans = 1;
                }
                for (int next : adj[node]) {
                    if (visited[next]) continue;

                    visited[next] = true;
                    q.add(new int[]{next, depth + 1});
                }
            }
        }

        return ans;

    }
}
