package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ17071 {

    static final int M = 999999;
    static int[] brotherTime = new int[500001];
    static int[][] subinTime = new int[2][500001];
    static int N,K;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static int calculate() {

        if (N == K) return 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        int time = 1;
        while (!q.isEmpty()) { // 최대 50만개의 정점.
            int size = q.size();
            int mod = time % 2;

            for (int i = 0 ; i < size; i++) {
                int subin = q.poll();
                check(subin - 1, time, q, mod);
                check(subin + 1, time, q, mod);
                check(subin * 2, time, q, mod);
            }

            time++;
        }

        return findMin(); // 최대 1000번

    }

    private static int findMin() {

        int time = 0;

        while (K <= 500000) {
            int mod = time % 2;
            // 방문한 정점이어야 하며, 해당 위치로 다시 갈 수 있으며, 동생보다 일찍 도착한 경우
            if (subinTime[mod][K] != M && brotherTime[K] != M &&
                    brotherTime[K] >= subinTime[mod][K]) {
                return brotherTime[K];
            }

            K+= ++time;
        }

        return -1;

    }

    private static void check(int subin, int time, Queue<Integer> q, int mod) {

        if (isInvalid(subin) || subinTime[mod][subin] != M ) return;

        subinTime[mod][subin] = time;
        q.add(subin);

    }

    private static boolean isInvalid(int subin) {
        return subin < 0 || subin > 500000;
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        Arrays.fill(brotherTime,M);
        Arrays.fill(subinTime[0],M);
        Arrays.fill(subinTime[1],M);
        subinTime[0][N] = 0;

        int time = 0;
        int ktime = K;
        while (ktime <= 500000) {
            brotherTime[ktime] = time++;
            ktime += time;
        }

    }
}
