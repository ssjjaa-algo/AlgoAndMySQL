package CodeTree;

import java.io.*;

public class 외주수익최대화하기DP {

    static class Node {
        int day;
        int cost;

        public Node(int day, int cost) {
            this.day=day;
            this.cost=cost;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Node[] nodes;
    static int[] dp;
    static int n;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        dp[0] = 0;
        int max = 0;

        /*

         */
        for (int i = 1; i <= n + 1; i++) {
            dp[i] = Math.max(dp[i],dp[i-1]);
            int day = i + nodes[i].day;
            int cost = nodes[i].cost;
            if (day > n + 1) continue;

            dp[day] = Math.max(dp[day], dp[i] + cost);

        }

        return dp[n + 1];
    }
    private static void init() throws IOException {

        String[] input;
        n = Integer.parseInt(br.readLine());

        nodes = new Node[n + 2];
        dp = new int[n + 2];

        int t,p;
        for (int i = 1; i<= n; i++) {
            input = br.readLine().split(" ");
            t = Integer.parseInt(input[0]);
            p = Integer.parseInt(input[1]);

            nodes[i] = new Node(t,p);
        }
        nodes[n+1] = new Node(0,0);
    }
}
