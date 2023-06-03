package Baekjoon;

import java.util.*;
import java.io.*;
public class BOJ14567 {

    static class Node {
        int number;
        int level;

        public Node(int number, int level) {
            this.number = number;
            this.level = level;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<Integer> graph[] = new ArrayList[N + 1];
        int[] degree = new int[N + 1];

        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        int a,b;
        for (int i=0; i<M; i++) {
            input =br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            graph[a].add(b); // a번 과목이 b번 과목의 선수과목이다.
            degree[b]++; // b의 진입차수를 증가시켜준다.
        }


        int[] sequence = Topological(graph,N,degree);

        for (int i=1; i<=N; i++) {
            sb.append(sequence[i]).append(" ");
        }

        System.out.println(sb);
        wr.close();
        br.close();
    }

    private static int[] Topological(List<Integer>[] graph,int N, int[] degree) {

        Queue<Node> q = findZero(graph,N,degree);
        int[] sequence = new int[N+1];

        while(!q.isEmpty()) {
            Node temp = q.poll();

            sequence[temp.number] = temp.level;

            for (int i=0; i < graph[temp.number].size(); i++) {

                int nextNumber = graph[temp.number].get(i);

                if (--degree[nextNumber] == 0) {
                    q.add(new Node(nextNumber,temp.level + 1));
                }
            }
        }

        return sequence;
    }

    private static Queue<Node> findZero(List<Integer>[] graph,int N, int[] degree) {

        Queue<Node> q = new ArrayDeque<>();

        for (int i=1; i <= N; i++) {
            if (degree[i] == 0) { // 진입 차수가 없는 최초 인원들은 방문 순서 1번.
                q.add(new Node(i,1));
            }
        }
        return q;
    }
}
