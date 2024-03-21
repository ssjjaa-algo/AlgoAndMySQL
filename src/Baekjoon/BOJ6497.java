package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ6497 {

    static int m, n;
    static int[] parents;
    static Edge[] edges;
    static int sumOfW;

    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        calculate();
    }

    private static void calculate() throws IOException {

        StringBuilder sb = new StringBuilder();
        while(true) {
            if (!init()) break;
            sb.append(kruskal()).append("\n");
        }

        System.out.print(sb);
    }

    private static int kruskal() {

        int res = 0;
        int cnt = 0;

        for (Edge edge : edges) {

            if (union(edge.v, edge.u)) {
                res += edge.w;
                if (++cnt == n - 1) break;
            }

        }

        return sumOfW - res;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (aRoot > bRoot) {
            parents[aRoot] = bRoot;
        }
        else parents[bRoot] = aRoot;

        return true;
    }

    private static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }

    private static boolean init() throws IOException {

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        if (n == 0 && m == 0) return false;

        sumOfW = 0;
        edges = new Edge[n];
        parents = new int[m];
        for (int i = 0; i < m; i++) {
            parents[i] = i;
        }

        int u, v, w;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);

            edges[i] = new Edge(u, v, w);
            sumOfW += w;
        }

        Arrays.sort(edges, Comparator.comparingInt(o -> o.w));

        return true;
    }
}
