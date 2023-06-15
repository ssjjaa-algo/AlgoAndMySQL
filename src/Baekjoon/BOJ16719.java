package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ16719 {

    static boolean[] visited;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        arr = str.toCharArray();
        visited = new boolean[str.length()];

        binarySearch(0,str.length());

        wr.write(sb.toString());
        br.close();
        wr.close();

    }

    private static void binarySearch(int left, int right) {

        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i=left; i<right; i++) {

            if(visited[i]) continue;

            if(min > arr[i]) {
                min = arr[i];
                idx = i; // 가장 작은 값의 idx 찾기
            }

        }

        if (min == Integer.MAX_VALUE) return;

        visited[idx] = true;

        for (int i=0; i < arr.length; i++) {
            if (visited[i]) sb.append(arr[i]);
        }
        sb.append("\n");

        binarySearch(idx + 1, right);
        binarySearch(left, idx);

    }
}
