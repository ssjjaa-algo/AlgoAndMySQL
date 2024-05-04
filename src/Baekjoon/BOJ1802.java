package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1802 {

    static char[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            arr = br.readLine().toCharArray();
            if (divide(0, arr.length - 1)) {
                sb.append("YES");
            }
            else sb.append("NO");

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static boolean divide(int start, int end) {

        if (start == end) {
            return true;
        }

        int mid = (start + end) / 2;

        for (int i = start; i < mid; i++) {
            if (arr[i] == arr[end - i]) return false;
        }

        return divide(start, mid - 1) && divide(mid + 1, end);
    }
}
