package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

public class BOJ25406 {

    static class Node implements Comparable<Node> {
        int num, second;

        public Node(int num, int second) {
            this.num = num;
            this.second = second;
        }

        @Override
        public int compareTo(Node o) {
            if (this.second == o.second) {
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.second, o.second);
        }
    }
    static TreeSet<Node> cntSet = new TreeSet<>();
    static TreeSet<Node> idxSet = new TreeSet<>();
    static Queue<Integer> q[];
    static int N;
    public static void main(String[] args) throws IOException {

        init();

        if (cntSet.last().second > Math.ceil((double) N / 2)) {
            System.out.println(-1);
            return;
        }
        calculate();

    }

    private static void calculate() {

        StringBuilder sb = new StringBuilder();

        int prev = 0;
        for (int i = N; i > 0; i--) {

            if (cntSet.last().second >= (i / 2) + 1) {

                Node node = cntSet.last();
                int num = node.num;
                prev = num;

                sb.append(q[num].peek()).append(" ");
                idxSet.remove(new Node(num, q[num].peek()));
                cntSet.remove(node);
                q[num].poll();

                check(num);
            }

            else {
                Node findNode = null;

                for (Node node : idxSet) {
                    if (node.num == prev) continue;

                    findNode = node;
                    break;
                }

                int num = findNode.num;
                prev = num;

                sb.append(findNode.second).append(" ");
                idxSet.remove(findNode);
                cntSet.remove(new Node(num, q[num].size()));
                q[num].poll();

                check(num);

            }
        }
        System.out.println(sb);
    }

    private static void check(int num) {

        if (q[num].isEmpty()) return;

        idxSet.add(new Node(num, q[num].peek()));
        cntSet.add(new Node(num, q[num].size()));

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        q = new ArrayDeque[N + 1];
        for (int i = 1; i <= N; i++) {
            q[i] = new ArrayDeque<>();
        }

        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(input[i - 1]);
            q[num].add(i);
        }

        for (int i = 1; i <= N; i++) {
            if (q[i].isEmpty()) continue;
            cntSet.add(new Node(i,q[i].size()));
            idxSet.add(new Node(i,q[i].peek()));
        }
    }
}