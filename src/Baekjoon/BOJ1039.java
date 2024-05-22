package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1039 {

    static String ans = "-1";
    static String N;
    static int K;
    static boolean[][] visited;

    static class Node {

        String num;
        int cnt;

        public Node(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = input[0];
        K = Integer.parseInt(input[1]);
        visited = new boolean[1000001][K + 1];

        System.out.println(calculate(N, K));

    }

    private static String calculate(String N, int K) {

        if (N.length() == 1) return ans;

        Queue<Node> q = new ArrayDeque<>();
        visited[Integer.parseInt(N)][0] = true;
        q.add(new Node(N, 0));

        int len = N.length();
        while (!q.isEmpty()) {
            Node cur = q.poll();
            String num = cur.num;
            if (cur.cnt == K) {
                ans = ans.compareTo(cur.num) > 0 ? ans : cur.num;
                continue;
            }

            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {

                    String newNum = swap(num, i, j);
                    if (newNum.equals("false")) continue;
                    int value = Integer.parseInt(newNum);
                    if (visited[value][cur.cnt + 1]) continue;

                    visited[value][cur.cnt + 1] = true;
                    q.add(new Node(newNum, cur.cnt + 1));
                }
            }
        }

        return ans;
    }

    private static String swap(String num, int i, int j) {

        char[] arr = num.toCharArray();

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        if (arr[0] == '0') return "false";
        return String.valueOf(arr);
    }
}
