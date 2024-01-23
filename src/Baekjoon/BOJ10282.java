package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10282 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static List<Node> adj[];
    static int n,d,c;
    public static void main(String[] args) throws IOException {
        calculate();
    }

    private static void calculate() throws IOException {

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            init();
            int[] ans = bfs();
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }

        System.out.print(sb);
    }

    private static int[] bfs() {

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(c,0));
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[c] = 0;
        int cnt = 0;
        int time = 0;

        while (!pq.isEmpty()) {

            Node temp = pq.poll();

            if (visited[temp.to]) continue;
            visited[temp.to] = true;
            cnt++;
            time = temp.weight;

            for (Node node : adj[temp.to]) {
                int next_dist = dist[temp.to] + node.weight;
                int next = node.to;

                if (dist[next] > next_dist) {
                    dist[next] = next_dist;
                    pq.add(new Node(next,next_dist));
                }
            }
        }

        return new int[]{cnt, time};
    }

    private static void init() throws IOException {

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int a, b, s;
        for (int i = 0 ; i < d; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            s = Integer.parseInt(input[2]);

            adj[b].add(new Node(a,s));
        }

    }
}
