package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {

    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        long ans = 0;
        while (pq.size() != 1) {

            long num1 = pq.poll();
            long num2 = pq.poll();

            ans += num1 + num2;
            pq.add(num1 + num2);
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }
    }
}
