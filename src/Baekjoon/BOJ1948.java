package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1948 {

    static class Edge implements Comparable<Edge>{
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to=to;
            this.cost=cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.cost, this.cost);
        }
    }

    static class Info {
        int dist;
        int parent;

        public Info(int dist, int parent) {
            this.dist=dist;
            this.parent=parent;
        }
    }

    static int n,m;
    static int start, end;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] count;
    static int[] dist;
    static List<Edge> cities[];
    static List<Edge> reverse[];
    static boolean[] visited;
    public static  void main(String[] args) throws IOException {

        init();
        calculate();
        System.out.println(dist[end]);

        System.out.println(reverse());

    }

    private static int reverse() {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(end);

        int cnt = 0;
        while (!q.isEmpty()) {

            int temp = q.poll();

            for (Edge e : reverse[temp]) {

                int next = e.to;
                int next_cost = e.cost;

                if (dist[temp] - next_cost == dist[next]) {
                    cnt++;

                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }
        return cnt;
    }

    private static void calculate() {

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        dist[start]= 0;

        while (!pq.isEmpty()) {

            Edge temp = pq.poll();
            int cur = temp.to;
            int cur_cost = temp.cost;

            if (dist[cur]> cur_cost) continue;

            for (Edge e : cities[cur]) {

                int next = e.to;
                int next_cost = cur_cost + e.cost;

                if (next_cost > dist[next]) {
                    dist[next] = next_cost;
                    pq.add(new Edge(next,next_cost));
                }
            }
        }

    }

    private static void init() throws IOException {
        String[] input;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        cities = new ArrayList[n + 1];
        reverse = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            cities[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
            dist[i] = 0;
        }

        int from, to, cost;
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            cost = Integer.parseInt(input[2]);

            cities[from].add(new Edge(to,cost));
            reverse[to].add(new Edge(from,cost));
        }

        input = br.readLine().split(" ");
        start = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);
    }
}
