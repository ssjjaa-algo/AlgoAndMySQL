package Programmers.Algorithm;

import java.util.*;

class 등산코스정하기 {

    static List<Node> graph[];
    static boolean[] isSummits;
    static boolean[] isGates;

    static class Node implements Comparable<Node> {
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

    public void init(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1];
        isSummits = new boolean[n + 1];
        isGates = new boolean[n + 1];

        int gatesLength = gates.length;
        int summitsLength = summits.length;

        for (int i = 0; i < gatesLength; i++) {
            isGates[gates[i]] = true;
        }
        for (int i = 0; i < summitsLength; i++) {
            isSummits[summits[i]] = true;
        }

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int[] path : paths) {

            if (isGates[path[0]] || isSummits[path[1]]) {
                graph[path[0]].add(new Node(path[1],path[2]));
            }

            else if (isGates[path[1]] || isSummits[path[0]]) {
                graph[path[1]].add(new Node(path[0],path[2]));
            }
            else {
                graph[path[0]].add(new Node(path[1],path[2]));
                graph[path[1]].add(new Node(path[0],path[2]));
            }
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n,paths,gates,summits);
        return dijkstra(n,gates,summits);
    }

    public int[] dijkstra(int n, int[] gates, int[] summits) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int len = gates.length;
        for (int i = 0; i < len; i++) {
            pq.add(new Node(gates[i],0));
            dist[gates[i]] = 0;
        }

        while(!pq.isEmpty()) {
            Node temp = pq.poll();
            int cur = temp.to;
            int cost = temp.cost;

            if (cost > dist[cur]) continue;

            for (Node node : graph[cur]) {
                int distance = Math.max(dist[cur],node.cost);
                if (dist[node.to] > distance) {
                    dist[node.to] = distance;
                    pq.add(new Node(node.to,distance));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        int vertex = 0;
        Arrays.sort(summits);
        for (int i = 0; i < summits.length; i++) {
            if (ans > dist[summits[i]]) {
                ans = dist[summits[i]];
                vertex = summits[i];
            }
        }

        return new int[] {vertex,ans};
    }

}
