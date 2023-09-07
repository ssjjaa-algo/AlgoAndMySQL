package Programmers.Algorithm;


import java.util.*;

public class 부대복귀 {

    static List<Integer> adj[];
    static int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        int length = sources.length;
        int[] answer = new int[length];
        init(n,roads);

        bfs(destination);
        for (int i = 0; i < length; i++) {
            answer[i] =
                    (dist[sources[i]] == Integer.MAX_VALUE ? -1 : dist[sources[i]]);
        }
        return answer;
    }

    public void bfs(int destination) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(destination);
        dist[destination] = 0;

        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int temp = q.poll();

                for (int j = 0; j < adj[temp].size(); j++) {
                    int next = adj[temp].get(j);

                    if (dist[next] == Integer.MAX_VALUE) {
                        dist[next] = cnt + 1;
                        q.add(next);
                    }
                }
            }
            cnt++;
        }
    }

    public void init(int n, int[][] roads) {
        adj = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        dist = new int[n + 1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        int x,y;
        for (int[] road : roads) {
            x = road[0];
            y = road[1];

            adj[x].add(y);
            adj[y].add(x);
        }
    }
}