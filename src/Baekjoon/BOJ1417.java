package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 제일 큰 사람의 투표를 1개씩 가져오며 first를 갱신시킨다
 *
 * 내림차순 정렬하여 하나씩 더해준다
 */
public class BOJ1417 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int first = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> { return Integer.compare(o2, o1);});
        for (int i = 0; i < N - 1; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(calculate(N, first, pq));
    }

    private static int calculate(int N, int first, PriorityQueue<Integer> pq) {
        if (N == 1) return 0;

        int cnt = 0;
        while (first++ <= pq.peek()) {
            pq.add(pq.poll() - 1);
            cnt++;
        }

        return cnt;
    }
}
