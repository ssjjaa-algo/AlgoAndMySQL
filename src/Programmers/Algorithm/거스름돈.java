package Programmers.Algorithm;

import java.util.*;

class 거스름돈 {
    public int solution(int n, int[] money) {

        Arrays.sort(money);
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] =  (dp[i] + dp[i - m]) % 1000000007;
            }
        }

        return dp[n];
    }
}