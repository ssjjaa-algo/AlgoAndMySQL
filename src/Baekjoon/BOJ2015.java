package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2015 {

    static int[] arr;
    static int N, K;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        arr = new int[N + 1];

        input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(input[i - 1]);
            arr[i] += arr[i - 1];
        }
    }

    private static void calculate() {

        Map<Integer, Integer> map = new HashMap<>();
        long res = 0;

        map.put(0, 1);
        for (int i = 1; i <= N; i++) {
            res += map.getOrDefault(arr[i] - K, 0);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        System.out.println(res);

    }
}
