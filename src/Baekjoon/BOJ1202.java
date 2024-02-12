package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1202 {

    static class Diamond {
        int m, v;

        public Diamond(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    static int[] bags;
    static int N,K;
    static Diamond[] diamonds;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        long ans = 0;
        int diamondIdx = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int bag : bags) {

            while (diamondIdx < N && bag >= diamonds[diamondIdx].m) {
                pq.add(diamonds[diamondIdx++].v);
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        bags = new int[K];
        diamonds = new Diamond[N];

        int m, v;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            m = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);

            diamonds[i] = (new Diamond(m, v));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);
        Arrays.sort(diamonds, Comparator.comparingInt(o -> o.m));
    }
}
