package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ2610 {

    static class Group {
        List<Integer> list = new ArrayList<>();

        public List<Integer> getList() {
            return list;
        }
    }
    static List<Integer> adj[];
    static List<Group> groupList = new ArrayList<>();
    static int N, M;

    public static void main(String[] args) throws IOException {

        init();
        makeGroup();
        findRepresentatives();
    }

    private static void findRepresentatives() {

        List<Integer> ans = new ArrayList<>();
        for (Group g : groupList) {
            int representative = Integer.MAX_VALUE;
            int count = Integer.MAX_VALUE;
            for (int i : g.getList()) {
                int candidate = calculateDist(i, new boolean[N + 1]);
                if (count > candidate) {
                    representative = i;
                    count = candidate;
                }
            }
            ans.add(representative);
        }

        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        sb.append(groupList.size()).append("\n");
        for (int i : ans) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    private static int calculateDist(int num, boolean[] visited) {

        Queue<Integer> q = new ArrayDeque<>();
        visited[num] = true;
        q.add(num);
        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int next : adj[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }

            }
            cnt++;
        }

        return cnt;
    }

    private static void makeGroup() {

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                Group group = new Group();
                visited[i] = true;
                group.getList().add(i);
                dfs(i, group, visited);
                groupList.add(group);
            }
        }
    }

    private static void dfs(int num, Group group, boolean[] visited) {

        for (int next : adj[num]) {
            if (!visited[next]) {
                visited[next] = true;
                group.getList().add(next);
                dfs(next, group, visited);
            }
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            adj[a].add(b);
            adj[b].add(a);
        }
    }
}
