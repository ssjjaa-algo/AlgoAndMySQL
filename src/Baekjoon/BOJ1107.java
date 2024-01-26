package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1107 {

    static boolean[] broken = new boolean[10];
    static int N;
    static int M;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        init();
        ans = Math.abs(100 - N);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= 9; i++) {
            if (broken[i]) continue;
            sb.append(i);
            dfs(sb);
            sb.deleteCharAt(0);
        }
        System.out.println(ans);
    }

    private static void dfs(StringBuilder sb) {

        if (sb.length() > 6) return;

        ans = Math.min(ans, calculate(sb));

        for (int i = 0; i <= 9; i++) {
            if (broken[i]) continue;
            sb.append(i);
            dfs(sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static int calculate(StringBuilder sb) {

        return Math.abs(N - Integer.parseInt(sb.toString())) + sb.length();

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        if (M == 0) return;
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            broken[Integer.parseInt(input[i])] = true;
        }
    }
}
