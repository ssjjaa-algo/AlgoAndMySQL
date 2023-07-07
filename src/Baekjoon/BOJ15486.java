package Baekjoon;

import java.io.*;

public class BOJ15486 {
    static class pair {
        int day;
        int cost;

        public pair(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static String[] input;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];
        pair[] info = new pair[N + 2];
        int max = 0;

        int day,cost;
        for (int i=1; i<=N; i++) {
            input = br.readLine().split(" ");
            day = Integer.parseInt(input[0]);
            cost = Integer.parseInt(input[1]);
            info[i] = new pair(day,cost);

        }
        info[N+1] = new pair(0,0);

        for (int idx=1; idx<=N + 1; idx++) {

            if (max < dp[idx]) {
                max = dp[idx];
            }
            day = info[idx].day;
            cost = info[idx].cost;

            if (idx + day > N + 1) continue;

            dp[idx + day] = Math.max(max + cost, dp[idx + day]);
        }

        System.out.println(dp[N+1]);
    }
}
