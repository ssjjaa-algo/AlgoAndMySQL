package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1713 {

    static Node[] students = new Node[101];

    static class Node implements Comparable<Node> {
        int num, cnt, time;

        public Node(int num, int cnt, int time) {
            this.num = num;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        calculate(N, M, input);

    }

    private static void calculate(int N, int M, String[] input) {

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int recommend = Integer.parseInt(input[i]);

            int size = list.size();
            if (students[recommend] != null) {
                students[recommend].cnt++;
            }
            else {
                Node node = new Node(recommend, 1, i);
                if (size >= N) {
                    list.sort((o1, o2) -> {
                        if (o1.cnt == o2.cnt) {
                            return Integer.compare(o2.time, o1.time);
                        }
                        return Integer.compare(o2.cnt, o1.cnt);
                    });

                    Node rm = list.remove(size - 1);
                    students[rm.num] = null;
                }
                students[recommend] = node;
                list.add(node);
            }
        }

        list.sort((o1, o2) -> {return Integer.compare(o1.num, o2.num);});
        StringBuilder sb = new StringBuilder();
        for (Node node : list) {
            sb.append(node.num).append(" ");
        }
        System.out.println(sb);

    }
}
