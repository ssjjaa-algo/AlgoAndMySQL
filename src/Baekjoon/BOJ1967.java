package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1967 {

    static int N;

    static int ans = 0;

    static class Node {

        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Node> adj[];
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        dfs(1,0);

        System.out.println(ans);
    }

    private static void dfs(int vertex, int sum) {

        ans = Math.max(ans, sum);

        for (Node node : adj[vertex]) {

            if (visited[node.to]) continue;

            visited[node.to] = true;
            dfs(node.to, sum + node.weight);
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        String[] input;

        int u, v, w;
        for (int i = 0; i < N - 1; i++) {
            input = br.readLine().split(" ");

            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);

            adj[u].add(new Node(v,w));
            adj[v].add(new Node(u,w));
        }

    }
}
