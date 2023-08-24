package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ14621 {

    static class Edge implements Comparable<Edge>{
        int u;
        int v;
        int distance;

        public Edge(int u, int v, int distance) {
            this.u = u;
            this.v = v;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance,o.distance);
        }

    }
    static char[] genders;
    static Edge[] edges;
    static int[] parents;
    static int N,M;
    public static void main(String[] args) throws IOException{

        init();
        System.out.println(kruskal());
    }

    private static int kruskal() {

        int cnt = 0;
        int res = 0;
        for (int idx = 0; idx < M; idx++) {

            int u = edges[idx].u;
            int v = edges[idx].v;
            if (!diffrentGender(u,v)) continue;

            if (union(u,v)) {
                res += edges[idx].distance;
                if(++cnt == N - 1) {
                    return res;
                }
            }

        }

        return -1;
    }
    private static boolean diffrentGender(int u, int v) {

        if (genders[u] == genders[v]) return false;

        return true;
    }
    private static int find(int num) {

        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
    private static boolean union(int a, int b) {

        int Aroot = find(a);
        int Broot = find(b);

        if (Aroot == Broot) return false;

        if (Aroot > Broot) {
            parents[Aroot] = Broot;
        }
        else {
            parents[Broot] = Aroot;
        }

        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        genders = new char[N + 1];
        edges = new Edge[M];
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            genders[i] = input[i - 1].charAt(0);
        }

        int u,v,d;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            d = Integer.parseInt(input[2]);
            edges[i] = new Edge(u,v,d);
        }

        Arrays.sort(edges);
    }
}
