package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ5972 {

    static class Edge implements Comparable<Edge>{
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost,e.cost);
        }
    }
    static List<Edge> adj[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int MAX_VALUE = 999999999;
    static String[] input;
    public static void main(String[] args) throws IOException{

        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N ; i++) {
            adj[i] = new ArrayList<>();
        }

        int from, to, cost;
        for (int i = 0 ; i < M; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            cost = Integer.parseInt(input[2]);

            adj[from].add(new Edge(to,cost));
            adj[to].add(new Edge(from,cost));

        }

        System.out.println(dijkstra(1,N));
    }

    private static int dijkstra(int start, int N) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist,MAX_VALUE);

        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.add(new Edge(start,0));

        while (!pq.isEmpty()) {

            Edge temp = pq.poll();

            int cur = temp.to;
            int cost = temp.cost;

            if (cur == N) return dist[N];

            if (dist[cur] < cost) continue;
//
//            for (Edge e : adj[cur]) {
//                if (dist[e.to] > dist[cur] + e.cost){
//                    dist[e.to] = dist[cur] + e.cost;
//                    pq.add(new Edge(e.to, e.cost));
//                }

            for (int i = 0; i < adj[cur].size(); i++) {
                int next_to = adj[cur].get(i).to;
                int next_cost = adj[cur].get(i).cost + cost;

                if (dist[next_to] > next_cost) {
                    dist[next_to] = next_cost;
                    pq.add(new Edge(next_to, next_cost));
                }
            }
        }

        return dist[N];
    }
}
