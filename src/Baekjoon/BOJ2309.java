package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2309 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[] ans = new int[9];
    static int[] arr = new int[9];
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 9; i++) arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        dfs(0,0,0);

        for (int i = 0 ; i < 7; i++) sb.append(ans[i]).append("\n");

        System.out.println(sb.toString());

    }
    private static boolean dfs(int cnt, int start, int sum) {

        if (sum > 100) return false;

        if (cnt == 7) {
            if (sum == 100) return true;
        }

        for (int i = start; i < 9; i++) {
            ans[cnt] = arr[i];
            if (dfs(cnt + 1,i + 1, sum + ans[cnt])) return true;
        }

        return false;
    }
}
