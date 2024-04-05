package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15486Re {

    static class Node {
        int day, cost;

        public Node(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }
    static int N;
    static int[] dp;
    static Node[] node;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int max = 0;

        for (int i = 1; i <= N + 1; i++) {

            if (max < dp[i]) {
                max = dp[i];
            }

            if (node[i].day + i > N + 1) continue;

            dp[i + node[i].day] = Math.max(max + node[i].cost, dp[i + node[i].day]);
        }

        System.out.println(dp[N + 1]);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 2];
        node = new Node[N + 2];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            node[i] = new Node(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        node[N + 1] = new Node(0, 0);
    }
}
