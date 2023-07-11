package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ21278 {

    static String[] input;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N,M;
    static int[] select = new int[2];
    static int ans = Integer.MAX_VALUE;
    static int firstBuilding;
    static int secondBuilding;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb =new StringBuilder();

        init();
        makeAdj();

        dfs(1,0);
        sb.append(firstBuilding).append(" ").append(secondBuilding).append(" ").append(ans * 2);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void dfs(int start, int cnt) {

        if (cnt == 2) {
            int min = 0;
            for (int i= 1; i<= N; i++) {
                min += bfs(i);
            }
            if (ans > min) {
                ans = min;
                firstBuilding = select[0];
                secondBuilding = select[1];
            }
            return;
        }

        for (int i=start; i<=N; i++) {
            select[cnt] = i;
            dfs(start + 1,cnt + 1);
        }


    }

    private static int bfs(int num) {

        for (int i=0; i<2; i++) {
            if (num == select[i]) return 0;
        }

        boolean[] visited = new boolean[N + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(num);
        visited[num] = true;

        int cnt = 1;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int now = q.poll();
                int next = 0;
                for (int j = 0; j < adj[now].size(); j++) {
                    next = adj[now].get(j);
                    if(next == select[0] || next == select[1]) return cnt;

                    if(!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
            cnt++;
        }

        return 0;
    }

    private static void makeAdj() throws IOException{
        int a,b;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            adj[a].add(b);
            adj[b].add(a);
        }
    }
    private static void init() throws IOException{
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();
    }
}
