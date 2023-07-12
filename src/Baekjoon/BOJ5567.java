package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ5567 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> tree[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        init(N,M);

        System.out.println(bfs(1));

    }
    private static int bfs(int num) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(num);
        visited[num] = true;

        int depth = 0;
        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            for (int i=0; i<size; i++) {
                int temp = q.poll();

                for (int j = 0; j < tree[temp].size(); j++ ) {
                    int next = tree[temp].get(j);
                    if(!visited[next]) {
                        cnt++;
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }

            if (++depth == 2) break;
        }

        return cnt;
    }
    private static void init(int N, int M) throws IOException{
        String[] input;
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <=N; i++)
            tree[i] = new ArrayList<>();
        int a,b;
        for (int i=0; i<M; i++) {
            input =br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            tree[a].add(b);
            tree[b].add(a);
        }

    }
}
