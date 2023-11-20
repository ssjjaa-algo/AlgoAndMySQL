package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ22868 {

    static List<Integer>[] adj;
    static int[] parents;
    static boolean[] visited;
    static int N,M,start,end;
    public static void main(String[] args) throws IOException {

        init();
        int ans = 0;
        ans += bfs(start,end);

        beforeEndToStartInit();

        ans += bfs(end,start);
        System.out.println(ans);
    }

    private static void beforeEndToStartInit() {

        Arrays.fill(visited,false);
        visited[start] = false;

        int check = parents[end];

        while(check != 0) {
            visited[check] = true;
            check = parents[check];
        }
        visited[start] = false;

    }

    private static int bfs(int start, int end) {

        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        visited[start] = true;

        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int parent = q.poll();

                for (int num : adj[parent]) {

                    if (!visited[num]) {
                        visited[num] = true;
                        parents[num] = parent;
                        q.add(num);
                    }
                }
            }

            cnt++;

            if (visited[end]) return cnt;
        }

        return cnt;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        parents = new int[N + 1];

        for (int i = 1; i<= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int u,v;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);

            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i<= N; i++) {
            Collections.sort(adj[i]);
        }

        input = br.readLine().split(" ");
        start = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);
    }
}
