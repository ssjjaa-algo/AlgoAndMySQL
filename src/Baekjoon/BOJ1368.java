package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1368 {

    static class Edge implements Comparable<Edge>{
        int u,v,w;

        public Edge(int u, int v, int w) {
            this.u=u;
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w,o.w);
        }
    }
    static int N;
    static int[][] map;
    static int[] parents;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    public static void calculate() {

        int idx = 0;
        int res = 0;

        while (idx != N) {

            Edge edge = pq.poll();

            int u = edge.u;
            int v = edge.v;

            if (union(u,v)) {
                res += edge.w;
                idx++;
            }
        }
        System.out.println(res);
    }

    public static boolean union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (aRoot > bRoot) {
            parents[aRoot] = bRoot;
        }
        else parents[bRoot] = aRoot;

        return true;
     }

     public static int find(int num) {

        if (parents[num] == num) return num;

        return parents[num] = find(parents[num]);
     }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.add(new Edge(i,0,num));
        }

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                int to = Integer.parseInt(input[j]);
                pq.add(new Edge(i + 1,j + 1,to));
            }
        }
    }
}
