package Baekjoon;

import java.io.*;
import java.util.*;

/*
시간 초과 : 인접리스트에 모든 원소를 넣고 bfs를 돌렸을 때 났음.
그래서 MST가 생성될 때 선택한 간선들만 인접리스트에 넣고 나서 bfs를 돌려 시간초과 해결

 */
public class BOJ23034 {

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost,o.cost);
        }
    }

    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Node> adj[];
    static Edge[] edgeArr;
    static int[] parents;
    static int[][] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb =new StringBuilder();

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        edgeArr = new Edge[M];
        parents = new int[N+1];
        adj = new ArrayList[N+1];
        dist = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++)
                dist[i][j] = -1;
        }

        for (int i=1; i<=N; i++) {
            parents[i] = i;
            adj[i] = new ArrayList<>();
        }

        int from, to, cost;
        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            cost = Integer.parseInt(input[2]);

            edgeArr[i] = new Edge(from,to,cost);
        }

        Arrays.sort(edgeArr); // 비용 오름차순 정렬
        long res = kruskal(N); // 1. 최소 신장 트리 만들기

        int Q = Integer.parseInt(br.readLine());

        for (int i=1; i<=N; i++) { // 지점마다 최대값 찾아가기
            bfs(N,i);
        }


        long ans = 0;
        for (int i=0; i<Q; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);

            sb.append(res - dist[from][to]).append("\n");
        }

        wr.write(sb.toString());
        wr.close();
        br.close();

    }

    private static void bfs(int N, int start) {

        Arrays.fill(visited,false);
        dist[start][start] = 0;
        visited[start] = true;

        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);

        while (!q.isEmpty()) {

            int temp = q.poll();

            for (int i=0; i < adj[temp].size(); i++) {

                int next = adj[temp].get(i).to;
                int cost = adj[temp].get(i).cost;

                if (visited[next]) continue;

                dist[start][next] = Math.max(Math.max(dist[start][next],dist[start][temp]),cost);
                visited[next] = true;
                q.add(next);

            }

        }
    }

    private static long kruskal(int N) {

        int cnt = 0;
        long res = 0;

        int size = edgeArr.length;

        for (int i=0; i < size; i++) {

            if (union(edgeArr[i].from,edgeArr[i].to)) {
                res += edgeArr[i].cost;
                adj[edgeArr[i].from].add(new Node(edgeArr[i].to,edgeArr[i].cost));
                adj[edgeArr[i].to].add(new Node(edgeArr[i].from,edgeArr[i].cost));

                if (++cnt == N-1) break;
            }
        }

        return res;
    }

    private static boolean union(int a, int b) {

        int Aroot = find(a);
        int Broot = find(b);


        if (Aroot == Broot) return false;

        if (Aroot > Broot)
            parents[Aroot] = Broot;

        else parents[Broot] = Aroot;

        return true;
    }

    private static int find(int num) {

        if( parents[num] == num) return num;

        return parents[num] = find(parents[num]);
    }


}
