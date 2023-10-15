package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ15681 {

    static int N,R,Q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static List<Integer> adj[];

    static boolean[] visited;
    static int[] countOfChildren;
    public static void main(String[] args) throws IOException {

        init();
        makeTree(R);
        calculate();
    }

    private static void calculate() throws IOException {

        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < Q; i++) {
            num = Integer.parseInt(br.readLine());
            sb.append(countOfChildren[num]).append("\n");
        }

        System.out.print(sb);
    }

    private static int makeTree(int root) {

        for (int i = 0; i < adj[root].size(); i++) {
            int next = adj[root].get(i);
            if (!visited[next]) {
                visited[next] = true;
                countOfChildren[root] += makeTree(next);
            }
        }

        return countOfChildren[root];
    }


    private static void init() throws IOException {

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        R = Integer.parseInt(input[1]);
        Q = Integer.parseInt(input[2]);

        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        visited[R] = true;
        countOfChildren = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            countOfChildren[i] = 1;
        }

        int a,b;
        for (int i = 0; i < N - 1; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            adj[a].add(b);
            adj[b].add(a);
        }

    }
}
