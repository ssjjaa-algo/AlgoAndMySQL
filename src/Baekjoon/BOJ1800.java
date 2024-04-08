package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1800 {

    static class Node implements Comparable<Node> {
        int to, weight;
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int N, P, K;
    static List<Node> adj[];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int left = 0;
        int right = 1000000;

        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                right = mid - 1;
                ans = mid;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean dijkstra(int mid) {

        int[] count = new int[N + 1];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (count[cur.to] < cur.weight) continue;

            for (Node node : adj[cur.to]) {

                int next = node.to;
                int cnt = 0;
                if (node.weight > mid) cnt++;
                int nextCnt = cur.weight + cnt;

                if (count[next] > nextCnt) {
                    pq.add(new Node(next, nextCnt));
                    count[next] = nextCnt;
                }
            }
        }

        return count[N] <= K;
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        P = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int u,v,w;
        for (int i = 0; i < P; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);
            adj[u].add(new Node(v,w));
            adj[v].add(new Node(u,w));
        }
    }
}
