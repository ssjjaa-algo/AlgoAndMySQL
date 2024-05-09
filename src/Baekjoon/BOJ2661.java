package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2661 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dfs(N, "");
        br.close();
    }

    private static boolean dfs(int N, String s) {

        int len = s.length();
        if (len == N) {
            System.out.println(s);
            return true;
        }

        for (int i = 1; i <= 3; i++) {

            if (canAppend(s + i, len + 1)) {
                if (dfs(N, s + i)) return true;
            }
        }

        return false;
    }

    private static boolean canAppend(String s, int len) {

        for (int i = 1; i <= len / 2; i++) {
            String left = s.substring(len - (2 * i), len - i);
            String right = s.substring(len - i, len);

            if (left.equals(right)) return false;
        }

        return true;

    }
}
