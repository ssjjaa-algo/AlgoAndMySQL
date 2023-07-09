package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2887 {

    static class Planet {
        int vertex;
        int x,y,z;

        public Planet(int vertex, int x, int y, int z) {
            this.vertex = vertex;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge>{
        int u,v,w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w,o.w);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Planet[] plantes;
    static List<Edge> edgeList = new ArrayList<>();
    static int[] parents;
    static int N;
    public static void main(String[] args) throws IOException{

        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        init();
        addEdge();
        System.out.println(calculate());

    }

    private static int calculate() {

        int cnt = 0;
        int idx = 0;
        int res = 0;
        while(true) {
            if (union(edgeList.get(idx).u, edgeList.get(idx).v)) {
                cnt++;
                res += edgeList.get(idx).w;
            }

            idx++;
            if (cnt == N-1) break;
        }

        return res;
    }
    private static void init() throws IOException{
        parents = new int[N];
        for (int i=0; i<N; i++) {
            parents[i] = i;
        }

        plantes = new Planet[N];
        String[] input;
        int x,y,z;
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            z = Integer.parseInt(input[2]);
            plantes[i] = new Planet(i,x,y,z);
        }


    }

    static int find(int num) {
        if (parents[num] == num) return num;
        else return parents[num] = find(parents[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        if (aRoot > bRoot) parents[aRoot] = bRoot;

        else parents[bRoot] = aRoot;

        return true;
    }

    private static void addEdge() {
        int w = 0;

        Arrays.sort(plantes, (x1,x2) -> Integer.compare(x1.x,x2.x));
        for (int i=1; i< N; i++) {
            w = plantes[i].x - plantes[i-1].x;
            edgeList.add(new Edge(plantes[i].vertex,plantes[i-1].vertex,w));
        }

        Arrays.sort(plantes, (y1,y2) -> Integer.compare(y1.y,y2.y));
        for (int i=1; i< N; i++) {
            w = plantes[i].y - plantes[i-1].y;
            edgeList.add(new Edge(plantes[i].vertex,plantes[i-1].vertex,w));
        }

        Arrays.sort(plantes, (z1,z2) -> Integer.compare(z1.z,z2.z));
        for (int i=1; i< N; i++) {
            w = plantes[i].z - plantes[i-1].z;
            edgeList.add(new Edge(plantes[i].vertex,plantes[i-1].vertex,w));
        }

        Collections.sort(edgeList);

    }
}
