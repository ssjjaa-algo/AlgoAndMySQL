package Programmers.Algorithm;

import java.util.*;
class 네트워크bfs {

    public int solution(int n, int[][] computers) {

        int answer = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                answer++;
                bfs(i,n,computers,visited);
            }
        }
        return answer;
    }

    public void bfs(int v, int n, int[][] computers, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();

        visited[v] = true;
        q.add(v);

        while (!q.isEmpty()) {

            int vertex = q.poll();
            for (int i = 0; i < n; i++) {
                if (computers[vertex][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

    }


}
