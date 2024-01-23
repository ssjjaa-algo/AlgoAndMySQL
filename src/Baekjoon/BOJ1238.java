package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ1238 {

    static int N,M,X;
    static List<Node> adj[];
    static List<Node> reverseAdj[];

    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        int[] dist = dijkstra(adj);
        int[] reverseDist = dijkstra(reverseAdj);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i] + reverseDist[i]);
        }
        System.out.println(ans);
    }

    private static int[] dijkstra(List<Node>[] graph) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist,99999999);
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(X,0));
        dist[X] = 0;

        while (!pq.isEmpty()) {

            Node temp = pq.poll();

            if (dist[temp.to] < temp.weight) continue;

            for (Node node : graph[temp.to]) {

                int next_dist = temp.weight + node.weight;
                int next = node.to;

                if (dist[next] > next_dist) {
                    dist[next] = next_dist;
                    pq.add(new Node(next, next_dist));
                }

            }
        }

        return dist;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]);

        adj = new ArrayList[N + 1];
        reverseAdj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            weight = Integer.parseInt(input[2]);

            adj[from].add(new Node(to,weight));
            reverseAdj[to].add(new Node(from,weight));
        }


    }

}
