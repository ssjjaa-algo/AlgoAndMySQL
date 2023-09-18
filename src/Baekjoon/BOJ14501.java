package Baekjoon;

import java.io.*;
public class BOJ14501 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static class Info {
        int date;
        int profit;

        public Info(int date, int profit) {
            this.date=date;
            this.profit=profit;
        }
    }

    static Info[] day;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        init();
        dfs(1,0,0);
        System.out.println(ans);
    }

    private static void dfs(int curDay, int sum, int before) {

        if (curDay == n + 1) {
            ans = Math.max(ans,sum);
            return;
        }

        if (curDay > n + 1) {
            ans = Math.max(ans,sum - day[before].profit);
            return;
        }

        for (int i = curDay; i <= n; i++) {
            dfs(i + day[i].date, sum + day[i].profit, i);
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        String[] input;
        day = new Info[n + 1];
        int date, profit;
        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            date = Integer.parseInt(input[0]);
            profit = Integer.parseInt(input[1]);

            day[i] = new Info(date,profit);
        }
    }
}
