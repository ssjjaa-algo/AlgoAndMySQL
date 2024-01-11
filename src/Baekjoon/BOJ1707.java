package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1707 {

    static final int red = 1;
    static final int blue = -1;
    static int[] color;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        calculate();

    }

    private static void calculate() throws IOException {

        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int V,E;

        while (K-- > 0) {
            boolean check = true;
            String[] input = br.readLine().split(" ");
            V = Integer.parseInt(input[0]);
            E = Integer.parseInt(input[1]);

            List<Integer>[] adj = makeAdj(V,E);

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {

                    if (!dfs(adj,i,1)) {
                        check = false;
                        break;
                    }
                }
            }

            sb.append(check ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    private static boolean dfs(List<Integer>[] adj, int cur, int num) {

        for (int i = 0; i < adj[cur].size(); i++) {

            int next = adj[cur].get(i);

            if (color[next] == num) return false;
            if (color[next] != 0 ) continue;

            color[next] = num * -1;
            if(!dfs(adj,next, num * -1)) return false;
        }

        return true;
    }

    private static List<Integer>[] makeAdj(int V, int E) throws IOException{

        List<Integer> adj[] = new ArrayList[V + 1];
        color = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        int u,v;
        for (int i = 0; i < E; i++) {
            String[] input = br.readLine().split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);

            adj[u].add(v);
            adj[v].add(u);
        }

        return adj;
    }
}
