package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ24042 {

    static int N,M;
    static List<Node> adj[];
    static long[] dist;
    /*
    M = 5
    어떤 지점까지 왔는데 7초가 걸렸고, 10초에 해당 횡단보도가 초록불로 이동한다.

    curTime = 7

    nextTime = 4

    nextTime += M;


     */

    static class Node implements Comparable<Node>{
        int start;
        long time;

        public Node(int start, long time) {
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {

        init();
        dijkstra();

    }

    private static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        dist[1] = 0;

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (dist[curNode.start] > curNode.time) continue;

            for (Node nextNode : adj[curNode.start]) {

                int next = nextNode.start;
                long nextTime = nextNode.time;

                if (curNode.time > nextTime) {
                    long temp = curNode.time - nextTime;
                    nextTime += ((temp) / M) * M;
                    if (temp % M != 0) nextTime += M;
                }

                nextTime++;
                if (dist[next] > nextTime) {
                    dist[next] = nextTime;
                    pq.add(new Node(next,nextTime));
                }
            }
        }

        System.out.println(dist[N]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        dist = new long[N + 1];
        Arrays.fill(dist,Long.MAX_VALUE);

        int u, v;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            adj[u].add(new Node(v,i));
            adj[v].add(new Node(u,i));
        }

    }
}
