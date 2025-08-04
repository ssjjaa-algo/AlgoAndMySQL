package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ14938 {

    static class Node implements Comparable<Node>{
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int n, m, r;
    static int[] items;
    static List<Node> adj[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);

        adj = new ArrayList[n + 1];
        items = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        input = br.readLine().split(" ");
        for (int i = 1; i < n + 1; i++) {
            items[i] = Integer.parseInt(input[i - 1]);
        }

        for (int i = 0; i < r; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            res = Math.max(res, dijkstra(i));
        }

        System.out.println(res);
    }

    static int dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        pq.add(new Node(start, 0));
        visited[start] = true;
        int sum = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node n : adj[cur.to]) {

                int nextCost = cur.cost + n.cost;
                if (nextCost > m) continue;

                visited[n.to] = true;
                pq.add(new Node(n.to, nextCost));
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) sum += items[i];
        }

        return sum;
    }
}
