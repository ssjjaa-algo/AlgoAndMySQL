package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3079 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        calculate(N, M, arr);
    }

    private static void calculate(int N, int M, int[] arr) {

        long left = 1L;
        long right = (1000000000L * 1000000000L);

        long ans = right;
        while (left <= right) {

            long mid = (left + right) / 2;
            long people = examine(mid, N, M, arr);

            if (people >= M) {
                right = mid - 1;
                ans = Math.min(ans,mid);
            }
            else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static long examine(long second, int N, int M, int[] arr) {

        long res = 0L;

        for (int i = 0; i < N; i++) {
            res += (second / arr[i]);
            if (res >= M) return res;
        }

        return res;
    }
}

/**
 *
 * 1. 시간을 기준으로 이분탐색한다.
 * 2. 가장 오래 걸리는 시간 : 1개의 심사대가 걸리는 시간 * 최대 사람 수(10억)
 *
 * 3. n초에 각 심사대가 몇명 씩이나 심사할 수 있는지 체크해서
 * 4. 할 수 있다면 시간 줄여보고 없으면 늘려보기
 */
