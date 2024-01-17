package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ2252 {

    static List<Integer> adj[];
    static int[] level;
    static int N,M;
     public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

         Queue<Integer> q = new ArrayDeque<>();
         StringBuilder sb = new StringBuilder();

         for (int i = 1; i <= N; i++) {
             if (level[i] == 0) q.add(i);
         }

         while (!q.isEmpty()) {

             int cur = q.poll();
             sb.append(cur).append(" ");

             for (int i = 0; i < adj[cur].size(); i++) {

                 int target = adj[cur].get(i);
                 level[target]--;

                 if (level[target] == 0 ) q.add(target);

             }

         }

        System.out.println(sb);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        level = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int prev, next;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            prev = Integer.parseInt(input[0]);
            next = Integer.parseInt(input[1]);

            adj[prev].add(next);
            level[next]++;
        }


    }
}
