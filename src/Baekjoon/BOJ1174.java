package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1174 {

    static int count = 0;
    static int[] arr = new int[10];
    static List<String> stringList = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        if (N >= 1024) { // 9876543210이 1023번째로 등장하는 마지막 감소하는 수임.
            System.out.println(-1);
            return;
        }

        for (int i=1; i<=10; i++) {
            dfs(0,i); // 자리수마다 dfs 시작
        }

        System.out.println(stringList.get(N - 1));

        wr.close();
        br.close();

    }

    private static void dfs(int start, int cnt) {

        if (start == cnt) {

            for (int i=0; i<cnt; i++) {
                sb.append(arr[i]);
            }
            stringList.add(sb.toString());
            System.out.println(sb.toString());
            sb.setLength(0);
        }

        for (int i=0; i< 10; i++) {

            if (start == 0) {
                arr[start] = i;
                dfs(start + 1, cnt);
            }

            else {
                if (arr[start - 1] > i) { // 이전의 수가 현재 수보다 크다면
                    arr[start] = i;
                    dfs(start + 1, cnt);
                }
            }

        }
    }


}
