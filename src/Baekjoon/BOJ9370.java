package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9370 {

    static int T;
    static int n, m, t;
    static int s, g, h;
    static int[] destination;

    static List<Node> adj[];
    static class Node implements Comparable<Node>{

        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        calculate();

    }

    private static void calculate() throws IOException {

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {

            init();

            // 후보군의 목적지에 대하여 다익스트라

            List<Integer> ans = new ArrayList<>();
            for (int dest : destination) {

                int first = dijkstra(s,g) + dijkstra(g, h) + dijkstra(h, dest);
                int second = dijkstra(s,h) + dijkstra(h, g) + dijkstra(g, dest);
                int minimumDist = dijkstra(s,dest);

                if (first == minimumDist || second == minimumDist) {
                    ans.add(dest);
                }
            }
            Collections.sort(ans);

            for (int num : ans) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int dijkstra(int start, int end) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (cur.cost > dist[cur.to]) continue;

            for (Node next : adj[cur.to]) {

                if (dist[next.to] > cur.cost + next.cost) {
                    dist[next.to] = cur.cost + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist[end];

    }

    private static void init() throws IOException {

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        t = Integer.parseInt(input[2]);

        destination = new int[t];
        adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        input = br.readLine().split(" ");

        s = Integer.parseInt(input[0]);
        g = Integer.parseInt(input[1]);
        h = Integer.parseInt(input[2]);

        int a, b, d;
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            d = Integer.parseInt(input[2]);

            adj[a].add(new Node(b,d));
            adj[b].add(new Node(a,d));
        }

        for (int i = 0; i < t; i++) {
            destination[i] = Integer.parseInt(br.readLine());
        }
    }
}

