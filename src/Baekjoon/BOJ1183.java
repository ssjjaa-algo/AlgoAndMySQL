package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1183 {

    /**
     *
     * 마법사의 약속 시간은 a1, a2...
     * 도착 시간은 b1, b2...
     * 약속 시간을 T만큼 미루기 ->  | (a1 + t - bi) |
     *
     * 1. 우선 ai - bi를 구한다.
     * 2. ai - bi를 0이랑 가깝게 하는 어떤 숫자들 (t)를 구해보기.
     *
     */

    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            arr[i] = a - b;
        }

        Arrays.sort(arr);
        if (N % 2 == 1) {
            System.out.println(1);
        }
        else {
            System.out.println(arr[N / 2] - arr[N / 2 - 1] + 1);
        }
    }
}
