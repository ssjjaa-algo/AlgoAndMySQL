package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ1939 {

    static List<Node> adj[];
    static int N,M,start,end;
    static int Max = 0;
    static class Node {
        int next;
        int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        int left = 0;
        int right = Max;
        int ans = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (!bfs(mid)) {
                right = mid - 1;
            }
            else {
                ans = Math.max(ans,mid);
                left = mid + 1;
            }
        }

        System.out.println(ans);

    }

    private static boolean bfs(int num) {

        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(start,0));
        visited[start] = true;

        while (!q.isEmpty()) {

            Node curNode = q.poll();

            if (curNode.next == end) return true;
            for (Node node : adj[curNode.next]) {

                if(visited[node.next] || node.cost < num) continue;

                visited[node.next] = true;
                q.add(node);
            }
        }

        return false;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int a,b,c;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            c = Integer.parseInt(input[2]);
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
            Max = Math.max(Max,c);
        }

        input = br.readLine().split(" ");
        start = Integer.parseInt(input[0]);
        end = Integer.parseInt(input[1]);


    }
}
