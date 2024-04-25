package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1072 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long X = Long.parseLong(input[0]);
        long Y = Long.parseLong(input[1]);
        long Z = winPercent(X, Y);

        long left = 0;
        long right = X;
        long ans = Integer.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (winPercent(X + mid, Y + mid) > Z) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            }
            else left = mid + 1;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static long winPercent(long x, long y) {
        return y * 100 / x;
    }
}
