package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class BOJ2957 {

    static TreeSet<Integer> treeSet = new TreeSet<>();
    static int N;
    static int[] numbers;
    static long[] depth;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        StringBuilder sb = new StringBuilder();

        long ans = 0;
        for (int i = 0; i < N; i++) {

            depth[numbers[i]] = Math.max(depth[treeSet.lower(numbers[i])], depth[treeSet.higher(numbers[i])]) + 1;
            treeSet.add(numbers[i]);
            ans += depth[numbers[i]];
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        depth = new long[N + 2];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        treeSet.add(0);
        treeSet.add(N + 1);
        depth[0] = depth[N + 1] = -1;
    }

}
